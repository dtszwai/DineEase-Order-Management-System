package org.order.pojo.vo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * Value Object representing an Ordered ProductClientVO in the system. This object is used for transferring
 * ordered product data in a format that is convenient for the client, allowing them to see the
 * products they have ordered before.
 */
@Data
public class OrderedProductVO {
  /**
   * The name of the product.
   */
  private String name;

  /**
   * The description of the product.
   */
  private String description;

  /**
   * The total price of the products.
   */
  private BigDecimal totalPrice;

  /**
   * The image of the product.
   */
  private String image;

  /**
   * The quantity of the product ordered.
   */
  private Integer quantity;
}