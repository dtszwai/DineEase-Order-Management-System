package org.order.controllers.admin;

import org.order.pojo.dto.CustomerDTO;
import org.order.pojo.entity.CustomerToken;
import org.order.pojo.vo.CustomerVO;
import org.order.services.CustomerService;
import org.order.services.CustomerTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controller for handling customer related requests.
 */
@RestController
@RequestMapping("/admin/customers")
public class CustomerController {

  private final CustomerService customerService;
  private final CustomerTokenService customerTokenService;

  /**
   * Constructor for CustomerController.
   *
   * @param customerService      the service to handle customer operations
   * @param customerTokenService the service to handle customer token operations
   */
  @Autowired
  public CustomerController(CustomerService customerService, CustomerTokenService customerTokenService) {
    this.customerService = customerService;
    this.customerTokenService = customerTokenService;
  }

  /**
   * Create a new customer.
   *
   * @param customerDTO the new customer data
   */
  @PostMapping
  public void createCustomer(@RequestBody CustomerDTO customerDTO) {
    customerService.createCustomer(customerDTO);
  }

  /**
   * Update a specific customer by id.
   *
   * @param id          the id of the customer
   * @param customerDTO the new customer data
   */
  @PutMapping("/{id}")
  public void updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
    customerDTO.setId(id);
    customerService.updateCustomer(customerDTO);
  }

  /**
   * Get a list of customers.
   *
   * @param customerDTO the query parameters for fetching customers
   * @return a list of CustomerVO
   */
  @GetMapping
  public List<CustomerVO> listCustomers(CustomerDTO customerDTO) {
    return customerService.listCustomers(customerDTO);
  }

  /**
   * Get a specific customer token by id.
   *
   * @param id the id of the customer
   * @return a CustomerToken object
   */
  @GetMapping("/{id}/token")
  public CustomerToken findToken(@PathVariable Long id) {
    return customerTokenService.getToken(id);
  }

  /**
   * Checkout a specific customer by id.
   *
   * @param id the id of the customer
   */
  @PostMapping("/{id}/checkout")
  public void checkoutCustomer(@PathVariable Long id) {
    customerService.checkoutCustomer(id);
  }
}