package org.order.pojo.dto.query;

import lombok.Data;

@Data
public class CategoryQueryDTO {

  /**
   * The ID of the category.
   */
  private Long id;

  /**
   * The name of the category.
   */
  private String name;

  /**
   * The status of the category. See {@link org.order.constants.CategoryStatus}.
   */
  private String status;

  /**
   * The offset for pagination.
   */
  private Integer offset;

  /**
   * The limit for pagination.
   */
  private Integer limit;

  /**
   * The search query.
   */
  private String search;
}
