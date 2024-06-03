package org.order.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.order.annotations.FillUpdate;
import org.order.enums.FillType;
import org.order.pojo.dto.query.CategoryQueryDTO;
import org.order.pojo.entity.Category;
import org.order.pojo.vo.CategoryAdminVO;
import org.order.pojo.vo.CategoryClientVO;
import java.util.List;

/**
 * Mapper for handling category related operations.
 */
@Mapper
public interface CategoryMapper {

  /**
   * Get a list of categories for client view.
   *
   * @return a list of CategoryClientVO
   */
  @Select("SELECT DISTINCT c.id, c.name, c.display_order, COUNT(CASE WHEN c.status = 'ACTIVE' AND p.status = 'ACTIVE' THEN p.id END) OVER (PARTITION BY c.id ) AS product_count, c.status FROM category c LEFT JOIN product p ON p.category_id = c.id ORDER BY c.display_order")
  List<CategoryClientVO> listCategoriesForClient();

  /**
   * Get a list of categories.
   *
   * @param query the query parameters for fetching categories
   * @return a list of CategoryAdminVO
   */
  List<CategoryAdminVO> listCategories(CategoryQueryDTO query);

  /**
   * Update a specific category.
   *
   * @param category the new category data
   */
  @FillUpdate
  void updateCategory(Category category);

  /**
   * Create a new category.
   *
   * @param category the new category data
   */
  @FillUpdate(FillType.INSERT)
  void createCategory(Category category);

  /**
   * Delete a specific category by id.
   *
   * @param id the id of the category
   */
  @Delete("DELETE FROM category WHERE id = #{id}")
  void deleteCategory(Long id);

  /**
   * Get a specific category by id.
   *
   * @param id the id of the category
   * @return a CategoryAdminVO object
   */
  CategoryAdminVO getCategory(Long id);
}