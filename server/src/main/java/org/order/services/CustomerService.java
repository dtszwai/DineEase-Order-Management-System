package org.order.services;

import lombok.extern.slf4j.Slf4j;
import org.order.exceptions.BadRequestException;
import org.order.exceptions.NotFoundException;
import org.order.constants.CustomerStatus;
import org.order.constants.TableStatus;
import org.order.mappers.CustomerMapper;
import org.order.pojo.dto.CustomerDTO;
import org.order.pojo.entity.Customer;
import org.order.pojo.entity.Table;
import org.order.pojo.vo.CustomerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for managing customers.
 */
@Service
@Slf4j
public class CustomerService {

  private final CustomerMapper customerMapper;
  private final CustomerTokenService customerTokenService;
  private final TableService tableService;

  public CustomerService(CustomerMapper customerMapper, TableService tableService, CustomerTokenService customerTokenService) {
    this.customerMapper = customerMapper;
    this.tableService = tableService;
    this.customerTokenService = customerTokenService;
  }

  /**
   * Assign a table to a new customer.
   *
   * @param customerDTO the customer data to create.
   */
  @Transactional
  public void createCustomer(CustomerDTO customerDTO) {
    Long tableId = customerDTO.getTableId();

    if (tableId == null) {
      log.error("Failed to assign table to new customer: Table ID is null");
      throw new BadRequestException();
    }

    // Check if the table is already occupied
    var tables = tableService.listTables(tableId, null, null);
    if (tables.isEmpty()) {
      log.error("Failed to assign table to new customer: Table {} does not exist", tableId);
      throw new BadRequestException();
    }
    var table = tables.get(0);
    if (table.getStatus().equals(TableStatus.OCCUPIED)) {
      log.error("Failed to assign table to new customer: Table {} is already occupied", tableId);
      throw new BadRequestException();
    }
    if (table.getNumSeats() < customerDTO.getNumPeople()) {
      log.error("Failed to assign table to new customer: Table {} does not have enough seats", tableId);
      throw new BadRequestException();
    }

    // Create a new customer
    Customer customer = new Customer();
    BeanUtils.copyProperties(customerDTO, customer);

    customerMapper.createCustomer(customer);

    // Change the status of the table to occupied.
    Table updatedTable = Table.builder()
        .id(tableId)
        .status(TableStatus.OCCUPIED)
        .build();
    tableService.updateTable(updatedTable);

    CustomerVO customerVO = new CustomerVO();
    BeanUtils.copyProperties(customer, customerVO);

    // Create a new customer token
    customerTokenService.createToken(customer);
  }

  /**
   * Update a specific customer by id.
   *
   * @param customerDTO the new customer data
   */
  @Transactional
  public void updateCustomer(CustomerDTO customerDTO) {
    Customer existingCustomer = customerMapper.getCustomer(customerDTO.getId());
    if (existingCustomer == null) {
      log.error("Failed to update customer: Customer {} does not exist", customerDTO.getId());
      throw new NotFoundException();
    }

    if (existingCustomer.getStatus().equals(CustomerStatus.ACTIVE) && customerDTO.getStatus().equals(CustomerStatus.INACTIVE)) {
      // Change the status of the table to available.
      Table updatedTable = Table.builder()
          .id(existingCustomer.getTableId())
          .status(TableStatus.AVAILABLE)
          .build();
      tableService.updateTable(updatedTable);

      // Set token to expire
      var token = customerTokenService.getToken(existingCustomer.getId());
      token.setExpired(true);
      token.setExpiryTime(LocalDateTime.now());
      customerTokenService.updateToken(token);
    }

    Customer customer = new Customer();
    BeanUtils.copyProperties(customerDTO, customer);
    customerMapper.updateCustomer(customer);
  }

  /**
   * Get a list of customers.
   *
   * @param customerDTO the query parameters for fetching customers
   * @return a list of CustomerVO
   */
  public List<CustomerVO> listCustomers(CustomerDTO customerDTO) {
    List<Customer> customers = customerMapper.listCustomers(customerDTO);

    return customers.stream().map(customer -> {
      var token = customerTokenService.getToken(customer.getId());
      CustomerVO customerVO = new CustomerVO();
      BeanUtils.copyProperties(customer, customerVO);
      customerVO.setToken(token);
      return customerVO;
    }).toList();
  }

  /**
   * Checkout a specific customer by id.
   *
   * @param customerId the id of the customer
   */
  @Transactional
  public void checkoutCustomer(Long customerId) {
    Customer customer = customerMapper.getCustomer(customerId);

    // Check if the customer exists
    if (customer == null) {
      log.error("Failed to checkout customer: Customer {} does not exist", customerId);
      throw new NotFoundException();
    }

    // Check if the customer is already inactive
    if (customer.getStatus().equals(CustomerStatus.INACTIVE)) {
      log.error("Failed to checkout customer: Customer {} is already inactive", customerId);
      throw new BadRequestException();
    }

    // Change the status of the table to available.
    Table updatedTable = Table.builder()
        .id(customer.getTableId())
        .status(TableStatus.AVAILABLE)
        .build();
    tableService.updateTable(updatedTable);

    // Set token to expire
    var token = customerTokenService.getToken(customerId);
    token.setExpired(true);
    token.setExpiryTime(LocalDateTime.now());
    customerTokenService.updateToken(token);

    // Set the customer status to inactive
    customer.setStatus(CustomerStatus.INACTIVE);
    customerMapper.updateCustomer(customer);
  }
}
