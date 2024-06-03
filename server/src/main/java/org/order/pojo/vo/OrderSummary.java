package org.order.pojo.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderSummary {
  private Long customerId;

  private String status;

  private Long tableId;

  private Integer numPeople;

  private BigDecimal totalPrice;

  private LocalDateTime updatedAt;

  private String updatedBy;
}