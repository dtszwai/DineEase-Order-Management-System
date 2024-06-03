package org.order.pojo.dto.query;

import lombok.Data;

@Data
public class ProductQueryDTO {

  /**
   * The ID of the product.
   */
  private Long id;

  /**
   * The name of the product.
   */
  private String name;

  /**
   * The status of the product. See {@link org.order.constants.ProductStatus}.
   */
  private String status;

  /**
   * The category ID of the product.
   */
  private Long categoryId;

  /**
   * The offset for pagination.
   */
  private Integer offset;

  /**
   * The limit for pagination.
   */
  private Integer limit;

  /**
   * The search query for finding products.
   */
  private String search;
}