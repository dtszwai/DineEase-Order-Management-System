package org.order.pojo.dto.query;

import lombok.Data;

@Data
public class EmployeeQueryDTO {

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
}