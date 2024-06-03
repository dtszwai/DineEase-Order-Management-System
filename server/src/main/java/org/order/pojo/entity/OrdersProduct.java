package org.order.pojo.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrdersProduct {
  private Long ordersId;

  private Long productId;

  private Integer quantity;

  private BigDecimal totalPrice;
}
