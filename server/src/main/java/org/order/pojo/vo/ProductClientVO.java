package org.order.pojo.vo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * This class represents a ProductAdminVO entity. It is for the client view.
 */
@Data
public class ProductClientVO {

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
   * The category ID of the product.
   */
  private Long categoryId;

  /**
   * The category name of the product.
   */
  private String categoryName;

  private String categoryStatus;

  /**
   * The status of the product. See {@link org.order.constants.ProductStatus}.
   */
  private String status;

  /**
   * The display order of the product.
   */
  private Integer displayOrder;
}