package org.order.pojo.dto;

import lombok.Data;

/**
 * This class is a Data Transfer Object (DTO) representing an Employee. It is used to transfer data
 * between different parts of the application, specifically between the controller and the service
 * layer. It contains the username, password, and name of an Employee.
 */
@Data
public class EmployeeDTO {
  /**
   * The username of the Employee.
   */
  private String username;

  /**
   * The password of the Employee.
   */
  private String password;

  /**
   * The name of the Employee.
   */
  private String name;

  /**
   * {@link org.order.constants.EmployeeStatus} The status of the Employee.
   */
  private String status;
}