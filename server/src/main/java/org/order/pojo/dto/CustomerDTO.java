package org.order.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {

  /**
   * The unique ID of the customer.
   */
  private Long id;

  /**
   * The ID of the table where the customer is seated.
   */
  private Long tableId;

  /**
   * The status of the customer (e.g., ACTIVE, INACTIVE).
   */
  private String status;

  /**
   * The number of people in the customer's party.
   */
  private Integer numPeople;
}