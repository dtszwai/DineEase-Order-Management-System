package org.order.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestDTO {

  /**
   * The ID of the customer placing the order.
   */
  private Long customerId;

  /**
   * The list of products being ordered.
   */
  private List<OrdersProductDTO> products;
}