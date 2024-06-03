package org.order.controllers.admin;

import org.order.pojo.dto.ProductDTO;
import org.order.pojo.dto.query.ProductQueryDTO;
import org.order.pojo.vo.ProductAdminVO;
import org.order.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling product related requests.
 */
@RestController
@RequestMapping("/admin/products")
public class ProductController {

  private final ProductService productService;

  /**
   * Constructor for ProductController.
   *
   * @param productService the service to handle product operations
   */
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Get a list of products.
   *
   * @param query the query parameters for fetching products
   * @return a list of ProductAdminVO
   */
  @GetMapping
  public List<ProductAdminVO> listProducts(ProductQueryDTO query) {
    return productService.listProducts(query);
  }

  /**
   * Get a specific product by id.
   *
   * @param id the id of the product
   * @return a ProductAdminVO object
   */
  @GetMapping("/{id}")
  public ProductAdminVO getProduct(@PathVariable Long id) {
    return productService.getProduct(id);
  }

  /**
   * Update a specific product by id.
   *
   * @param id      the id of the product
   * @param product the new product data
   * @return a ResponseEntity
   */
  @PutMapping("/{id}")
  public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductDTO product) {
    product.setId(id);
    productService.updateProduct(product);
    return ResponseEntity.ok().build();
  }

  /**
   * Create a new product.
   *
   * @param product the new product data
   * @return a ResponseEntity
   */
  @PostMapping
  public ResponseEntity<Void> createProduct(@RequestBody ProductDTO product) {
    productService.createProduct(product);
    return ResponseEntity.ok().build();
  }

  /**
   * Delete a specific product by id.
   *
   * @param id the id of the product
   * @return a ResponseEntity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.ok().build();
  }
}