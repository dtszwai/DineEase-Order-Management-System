package org.order.controllers.admin;

import org.order.pojo.dto.CategoryDTO;
import org.order.pojo.dto.query.CategoryQueryDTO;
import org.order.pojo.vo.CategoryAdminVO;
import org.order.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling category related requests.
 */
@RestController
@RequestMapping("/admin/categories")
public class CategoryController {

  private final CategoryService categoryService;

  /**
   * Constructor for CategoryController.
   *
   * @param categoryService the service to handle category operations
   */
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  /**
   * Get a list of categories.
   *
   * @param query the query parameters for fetching categories
   * @return a list of CategoryAdminVO
   */
  @GetMapping
  public List<CategoryAdminVO> listCategories(CategoryQueryDTO query) {
    return categoryService.listCategory(query);
  }

  /**
   * Get a specific category by id.
   *
   * @param id the id of the category
   * @return a CategoryAdminVO object
   */
  @GetMapping("/{id}")
  public CategoryAdminVO getCategory(@PathVariable Long id) {
    return categoryService.getCategory(id);
  }

  /**
   * Update a specific category by id.
   *
   * @param id          the id of the category
   * @param categoryDTO the new category data
   * @return a ResponseEntity
   */
  @PutMapping("/{id}")
  public ResponseEntity<Void> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
    categoryDTO.setId(id);
    categoryService.updateCategory(categoryDTO);
    return ResponseEntity.ok().build();
  }

  /**
   * Create a new category.
   *
   * @param categoryDTO the new category data
   * @return a ResponseEntity
   */
  @PostMapping
  public ResponseEntity<Void> createCategory(@RequestBody CategoryDTO categoryDTO) {
    categoryService.createCategory(categoryDTO);
    return ResponseEntity.ok().build();
  }

  /**
   * Delete a specific category by id.
   *
   * @param id the id of the category
   * @return a ResponseEntity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.ok().build();
  }
}