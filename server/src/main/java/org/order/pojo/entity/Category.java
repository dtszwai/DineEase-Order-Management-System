package org.order.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * This class represents a Category entity. It contains the category's ID, name, display order,
 * active status, and timestamps for creation and updates.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

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
   * The ID of the user who created the category.
   */
  private Long createdBy;

  /**
   * The timestamp when the category was last updated.
   */
  private LocalDateTime updatedAt;

  /**
   * The ID of the user who last updated the category.
   */
  private Long updatedBy;
}