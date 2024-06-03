package org.order.pojo.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Value Object for Category for admin.
 */
@Data
public class CategoryAdminVO {

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

  /**
   * The timestamp when the category was created.
   */
  private LocalDateTime createdAt;

  /**
   * The employee who created the category.
   */
  private String createdBy;

  /**
   * The timestamp when the category was last updated.
   */
  private LocalDateTime updatedAt;

  /**
   * The employee who last updated the category.
   */
  private String updatedBy;

  /**
   * The number of products in the category.
   */
  private Integer productCount;
}