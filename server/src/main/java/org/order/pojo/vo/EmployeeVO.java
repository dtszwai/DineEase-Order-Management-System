package org.order.pojo.vo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVO {

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
   * The user who created the employee.
   */
  private String createdBy;

  /**
   * The user who last updated the employee.
   */
  private String updatedBy;

}