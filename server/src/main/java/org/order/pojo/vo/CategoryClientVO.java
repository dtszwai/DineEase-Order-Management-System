package org.order.pojo.vo;

import lombok.Data;

/**
 * Value Object for Category for the client.
 */
@Data
public class CategoryClientVO {

  /**
   * The ID of the category.
   */
  private Long id;

  /**
   * The name of the category.
   */
  private String name;

  /**
   * The display order of the category.
   */
  private Integer displayOrder;

  /**
   * The number of products in the category.
   */
  private Integer productCount;

  /**
   * The status of the category. See {@link org.order.constants.CategoryStatus}
   */
  private String status;

}