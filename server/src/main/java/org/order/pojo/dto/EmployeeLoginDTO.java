package org.order.pojo.dto;

import lombok.Data;

/**
 * Employee login DTO
 */
@Data
public class EmployeeLoginDTO {
  /**
   * The username of the employee.
   */
  private String username;

  /**
   * The password of the employee.
   */
  private String password;
}
