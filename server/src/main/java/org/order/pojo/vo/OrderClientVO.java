package org.order.pojo.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Value Object representing an Order in the system. This object is used for transferring order data
 * in a format that is convenient for the client, allowing them to see the orders they have placed
 * before.
 */
@Data
public class OrderClientVO {
  /**
   * The unique identifier of the order.
   */
  private Long id;

  /**
   * The list of products in the order.
   */
  private List<OrderedProductVO> products;

  /**
   * The total price of the order.
   */
  private BigDecimal totalPrice;

  /**
   * The date and time the order was created.
   */
  private LocalDateTime createdAt;

  private String status;
}