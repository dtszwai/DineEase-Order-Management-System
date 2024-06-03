package org.order.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.order.annotations.FillUpdate;
import org.order.enums.FillType;
import org.order.pojo.dto.CustomerDTO;
import org.order.pojo.entity.Customer;
import java.util.List;

/**
 * Mapper for handling customer related operations.
 */
@Mapper
public interface CustomerMapper {

  /**
   * Create a new customer.
   *
   * @param customer the new customer data
   */
  @Insert("INSERT INTO customer (table_id, num_people, created_at, created_by, updated_at, updated_by) VALUES (#{tableId}, #{numPeople}, #{createdAt}, #{createdBy}, #{updatedAt}, #{updatedBy});")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  @FillUpdate(FillType.INSERT)
  void createCustomer(Customer customer);

  /**
   * Get a specific customer by id.
   *
   * @param id the id of the customer
   * @return a Customer object
   */
  @Select("SELECT * FROM customer WHERE id = #{id}")
  Customer getCustomer(Long id);

  /**
   * Update a specific customer.
   *
   * @param customer the new customer data
   */
  @FillUpdate(FillType.UPDATE)
  void updateCustomer(Customer customer);

  /**
   * Get a list of customers.
   *
   * @param customerDTO the query parameters for fetching customers
   * @return a list of Customer
   */
  List<Customer> listCustomers(CustomerDTO customerDTO);
}