package org.order.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {

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
   * The status of the category. See {@link org.order.constants.CategoryStatus}
   */
  private String status;
}