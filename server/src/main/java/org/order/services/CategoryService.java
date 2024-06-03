package org.order.services;

import org.order.mappers.CategoryMapper;
import org.order.pojo.dto.CategoryDTO;
import org.order.pojo.dto.query.CategoryQueryDTO;
import org.order.pojo.entity.Category;
import org.order.pojo.vo.CategoryAdminVO;
import org.order.pojo.vo.CategoryClientVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service for handling category related operations.
 */
@Service
public class CategoryService {

  private final CategoryMapper categoryMapper;

  /**
   * Constructor for CategoryService.
   *
   * @param categoryMapper the mapper to handle category operations
   */
  public CategoryService(CategoryMapper categoryMapper) {
    this.categoryMapper = categoryMapper;
  }

  /**
   * Get a list of categories.
   *
   * @param query the query parameters for fetching categories
   * @return a list of CategoryAdminVO
   */
  public List<CategoryAdminVO> listCategory(CategoryQueryDTO query) {
    return categoryMapper.listCategories(query);
  }

  /**
   * Get a list of categories for client view.
   *
   * @return a list of CategoryClientVO
   */
  public List<CategoryClientVO> listCategory() {
    return categoryMapper.listCategoriesForClient();
  }

  /**
   * Update a specific category.
   *
   * @param categoryDTO the new category data
   */
  public void updateCategory(CategoryDTO categoryDTO) {
    var category = new Category();
    BeanUtils.copyProperties(categoryDTO, category);
    categoryMapper.updateCategory(category);
  }

  /**
   * Create a new category.
   *
   * @param categoryDTO the new category data
   */
  public void createCategory(CategoryDTO categoryDTO) {
    var category = new Category();
    BeanUtils.copyProperties(categoryDTO, category);
    categoryMapper.createCategory(category);
  }

  /**
   * Delete a specific category by id.
   *
   * @param id the id of the category
   */
  public void deleteCategory(Long id) {
    categoryMapper.deleteCategory(id);
  }

  /**
   * Get a specific category by id.
   *
   * @param id the id of the category
   * @return a CategoryAdminVO object
   */
  public CategoryAdminVO getCategory(Long id) {
    return categoryMapper.getCategory(id);
  }
}