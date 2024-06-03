package org.order.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Represents a table in the restaurant. It contains the table's ID and status.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Table {

  /**
   * The unique identifier of the table.
   */
  private Long id;

  /**
   * The current status of the table. This could be 'AVAILABLE' or 'OCCUPIED'.
   * {@link org.order.constants.TableStatus} for possible values.
   */
  private String status;

  /**
   * The number of seats available at the table.
   */
  private Integer numSeats;

  /**
   * The updated_at timestamp of the table.
   */
  private LocalDateTime updatedAt;

  /**
   * The id of the employee who updated the table.
   */
  private Long updatedBy;
}