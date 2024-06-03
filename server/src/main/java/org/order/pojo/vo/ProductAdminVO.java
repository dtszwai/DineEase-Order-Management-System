package org.order.pojo.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This class represents a ProductAdminVO entity. It is for the admin view.
 */
@Data
public class ProductAdminVO {

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
   * The status of the product. See {@link org.order.constants.ProductStatus}.
   */
  private String status;

  /**
   * The status of the category. See {@link org.order.constants.CategoryStatus}.
   */
  private String categoryStatus;

  /**
   * The category ID of the product.
   */
  private Long categoryId;

  /**
   * The category name of the product.
   */
  private String categoryName;

  /**
   * The display order of the product.
   */
  private Integer displayOrder;

  /**
   * The timestamp when the product was created.
   */
  private LocalDateTime createdAt;

  /**
   * The employee who created the product.
   */
  private String createdBy;

  /**
   * The timestamp when the product was last updated.
   */
  private LocalDateTime updatedAt;

  /**
   * The employee who last updated the product.
   */
  private String updatedBy;
}