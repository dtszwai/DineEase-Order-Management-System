package org.order.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * This class represents a table in the restaurant. It contains information about the table's ID,
 * status, customer ID and the time at which the customer was seated at the table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableVO {

  /**
   * The ID of the table.
   */
  private Long id;

  /**
   * The current status of the table. This could be 'AVAILABLE', 'OCCUPIED' or 'RESERVED'. See
   * {@link org.order.constants.TableStatus} for possible values.
   */
  private String status;

  /**
   * The id of the customer currently assigned to the table. This is null if the table is
   * 'AVAILABLE' or 'RESERVED'.
   */
  private Long customerId;

  /**
   * The time at which the customer was seated at the table. This is null if the table is
   * 'AVAILABLE' or 'RESERVED'.
   */
  private LocalDateTime seatedAt;

  /**
   * The number of seats at the table.
   */
  private Integer numSeats;

  /**
   * The number of people in the customer's party.
   */
  private Integer numPeople;
}