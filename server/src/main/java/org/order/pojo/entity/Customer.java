package org.order.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * This class represents a Customer in the system. It includes information about the customer such
 * as their ID, table ID, status, and timestamps for when the customer record was created and last
 * updated. It also includes the IDs of the employees who created and last updated the customer
 * record.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  /**
   * The unique ID of the customer.
   */
  private Long id;

  /**
   * The ID of the table where the customer is seated.
   */
  private Long tableId;

  /**
   * {@link org.order.constants.CustomerStatus} The status of the customer.
   */
  private String status;

  /**
   * The number of people in the customer's party.
   */
  private Integer numPeople;

  /**
   * The date and time when the customer record was created.
   */
  private LocalDateTime createdAt;

  /**
   * The ID of the employee who created the customer record.
   */
  private Long createdBy;

  /**
   * The date and time when the customer record was last updated.
   */
  private LocalDateTime updatedAt;

  /**
   * The ID of the employee who last updated the customer record.
   */
  private Long updatedBy;
}