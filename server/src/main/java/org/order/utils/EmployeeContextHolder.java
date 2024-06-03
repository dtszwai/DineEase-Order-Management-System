package org.order.utils;

/**
 * This class provides a context holder for storing the employee ID. It uses a ThreadLocal variable
 * to store the employee ID, which ensures that the employee ID is always accessible in the context
 * of the current thread of execution.
 */
public class EmployeeContextHolder {

  /**
   * ThreadLocal variable to store the employee ID.
   */
  private static final ThreadLocal<Long> EMPLOYEE_ID = new ThreadLocal<>();

  /**
   * Sets the employee ID in the current thread context.
   *
   * @param employeeId the employee ID to set.
   */
  public static void setEmployeeId(Long employeeId) {
    EMPLOYEE_ID.set(employeeId);
  }

  /**
   * Retrieves the employee ID from the current thread context.
   *
   * @return the employee ID, or null if no ID has been set in the current context.
   */
  public static Long getEmployeeId() {
    return EMPLOYEE_ID.get();
  }

  /**
   * Clears the employee ID from the current thread context.
   */
  public static void clear() {
    EMPLOYEE_ID.remove();
  }
}