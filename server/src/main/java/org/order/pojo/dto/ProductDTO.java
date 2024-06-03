package org.order.pojo.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * This class represents a Product entity.
 */
@Data
public class ProductDTO {

  /**
   * The ID of the product.
   */
  private Long id;

  /**
   * The name of the product.
   */
  private String name;

  /**
   * The description of the product.
   */
  private String description;

  /**
   * The price of the product.
   */
  private BigDecimal price;

  /**
   * The image of the product.
   */
  private String image;

  /**
   * The status of the product. See {@link org.order.constants.ProductStatus}
   */
  private String status;

  /**
   * The category ID of the product.
   */
  private Long categoryId;

  /**
   * The display order of the product.
   */
  private Integer displayOrder;
}