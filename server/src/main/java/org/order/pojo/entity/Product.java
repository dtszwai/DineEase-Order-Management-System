package org.order.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This class represents a Product entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
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
   * The category ID of the product.
   */
  private Long categoryId;

  /**
   * The display order of the product.
   */
  private Integer displayOrder;

  /**
   * The timestamp when the product was created.
   */
  private LocalDateTime createdAt;

  /**
   * The id of the employee who created the product.
   */
  private Long createdBy;

  /**
   * The timestamp when the product was last updated.
   */
  private LocalDateTime updatedAt;

  /**
   * The id of the employee who last updated the product.
   */
  private Long updatedBy;
}