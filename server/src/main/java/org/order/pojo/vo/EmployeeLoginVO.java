package org.order.pojo.vo;

import lombok.Builder;
import lombok.Data;

/**
 * This class represents the login response for an employee. It contains the employee's ID,
 * username, name, and JWT token.
 */
@Data
@Builder
public class EmployeeLoginVO {
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
   * The JWT token of the employee.
   */
  private String token;
}