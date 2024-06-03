package org.order.pojo.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents an Employee entity. It contains the employee's ID, username, name,
 * password, status, and timestamps for creation and updates.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

  /**
   * The ID of the employee.
   */
  private Long id;

  /**
   * The username of the employee.
   */
  private String username;

  /**
   * The name of the employee.
   */
  private String name;

  /**
   * The password of the employee.
   */
  private String password;

  /**
   * The status of the employee.
   */
  private String status;

  /**
   * The timestamp when the employee was created.
   */
  private LocalDateTime createdAt;

  /**
   * The timestamp when the employee was last updated.
   */
  private LocalDateTime updatedAt;

  /**
   * The ID of the user who created the employee.
   */
  private Long createdBy;

  /**
   * The ID of the user who last updated the employee.
   */
  private Long updatedBy;

}