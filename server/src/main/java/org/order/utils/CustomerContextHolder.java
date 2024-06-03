package org.order.utils;

/**
 * This class provides a context holder for storing the customer ID. It uses a ThreadLocal variable
 * to store the customer ID, which ensures that the customer ID is always accessible in the context
 * of the current thread of execution.
 */
public class CustomerContextHolder {

  /**
   * ThreadLocal variable to store the customer ID.
   */
  private static final ThreadLocal<Long> CUSTOMER_ID = new ThreadLocal<>();

  /**
   * Sets the customer ID in the current thread context.
   *
   * @param customerId the customer ID to set.
   */
  public static void setCustomerId(Long customerId) {
    CUSTOMER_ID.set(customerId);
  }

  /**
   * Retrieves the customer ID from the current thread context.
   *
   * @return the customer ID, or null if no ID has been set in the current context.
   */
  public static Long getCustomerId() {
    return CUSTOMER_ID.get();
  }
}