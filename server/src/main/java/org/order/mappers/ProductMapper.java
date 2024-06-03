package org.order.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.order.annotations.FillUpdate;
import org.order.enums.FillType;
import org.order.pojo.dto.query.ProductQueryDTO;
import org.order.pojo.entity.Product;
import org.order.pojo.vo.ProductClientVO;
import org.order.pojo.vo.ProductAdminVO;
import java.util.List;

/**
 * Mapper for handling product related operations.
 */
@Mapper
public interface ProductMapper {

  /**
   * Get a list of products.
   *
   * @param query the query parameters for fetching products
   * @return a list of ProductAdminVO
   */
  List<ProductAdminVO> listProducts(ProductQueryDTO query);

  /**
   * Get a list of products for client view.
   *
   * @param categoryId the category ID to filter products by
   * @return a list of ProductClientVO
   */
  List<ProductClientVO> listProductsForClient(Integer categoryId);

  /**
   * Update a specific product.
   *
   * @param product the new product data
   */
  @FillUpdate
  void updateProduct(Product product);

  /**
   * Create a new product.
   *
   * @param product the new product data
   */
  @FillUpdate(FillType.INSERT)
  void createCategory(Product product);

  /**
   * Delete a specific product by id.
   *
   * @param id the id of the product
   */
  @Delete("DELETE FROM product WHERE id = #{id}")
  void deleteProduct(Long id);

  /**
   * Get a specific product by id.
   *
   * @param id the id of the product
   * @return a ProductAdminVO object
   */
  ProductAdminVO getProduct(Long id);
}