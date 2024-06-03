package org.order.pojo.entity;

import lombok.Data;
import java.math.BigDecimal;

/**
 * This class represents an order request. It contains the details of an order that a customer wants
 * to place.
 */
@Data
public class Orders {
  /**
   * The ID of the order.
   */
  private Long id;

  /**
   * The ID of the customer placing the order.
   */
  private Long customerId;

  /**
   * {@link org.order.constants.OrderStatus} The status of the order.
   */
  private String status;

  /**
   * The total price of the order.
   */
  private BigDecimal totalPrice;
}