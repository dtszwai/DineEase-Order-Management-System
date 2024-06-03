package org.order.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.pojo.entity.CustomerToken;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVO {

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

  /**
   * The token of the customer.
   */
  private CustomerToken token;
}