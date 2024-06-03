package org.order.pojo.dto;

import lombok.Data;

/**
 * This class represents a OrdersProductDTO. It contains the ID of the product being ordered and the
 * quantity of the product being ordered.
 */
@Data
public class OrdersProductDTO {

  /**
   * The ID of the order that the product is being ordered in.
   */
  private Long ordersId;

  /**
   * The ID of the product being ordered.
   */
  private Long productId;

  /**
   * The quantity of the product being ordered.
   */
  private Integer quantity;
}