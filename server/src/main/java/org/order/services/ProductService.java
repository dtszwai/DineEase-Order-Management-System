package org.order.services;

import org.order.mappers.ProductMapper;
import org.order.pojo.dto.ProductDTO;
import org.order.pojo.dto.query.ProductQueryDTO;
import org.order.pojo.entity.Product;
import org.order.pojo.vo.ProductAdminVO;
import org.order.pojo.vo.ProductClientVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service for handling product related operations.
 */
@Service
public class ProductService {

  private final ProductMapper productMapper;

  /**
   * Constructor for ProductService.
   *
   * @param productMapper the mapper to handle product operations
   */
  public ProductService(ProductMapper productMapper) {
    this.productMapper = productMapper;
  }

  /**
   * Get a list of products.
   *
   * @param query the query parameters for fetching products
   * @return a list of ProductAdminVO
   */
  public List<ProductAdminVO> listProducts(ProductQueryDTO query) {
    return productMapper.listProducts(query);
  }

  /**
   * Get a list of products for client view.
   *
   * @param categoryId the category ID to filter products by
   * @return a list of ProductClientVO
   */
  public List<ProductClientVO> listProducts(Integer categoryId) {
    return productMapper.listProductsForClient(categoryId);
  }

  /**
   * Update a specific product.
   *
   * @param productDTO the new product data
   */
  public void updateProduct(ProductDTO productDTO) {
    Product product = new Product();
    BeanUtils.copyProperties(productDTO, product);
    productMapper.updateProduct(product);
  }

  /**
   * Create a new product.
   *
   * @param productDTO the new product data
   */
  public void createProduct(ProductDTO productDTO) {
    Product product = new Product();
    BeanUtils.copyProperties(productDTO, product);
    productMapper.createCategory(product);
  }

  /**
   * Delete a specific product by id.
   *
   * @param id the id of the product
   */
  public void deleteProduct(Long id) {
    productMapper.deleteProduct(id);
  }

  /**
   * Get a specific product by id.
   *
   * @param id the id of the product
   * @return a ProductAdminVO object
   */
  public ProductAdminVO getProduct(Long id) {
    return productMapper.getProduct(id);
  }
}