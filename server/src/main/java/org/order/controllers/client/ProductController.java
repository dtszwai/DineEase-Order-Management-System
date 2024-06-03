package org.order.controllers.client;

import org.order.pojo.vo.ProductClientVO;
import org.order.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controller class for handling client requests related to products.
 */
@RestController("clientProductController")
@RequestMapping("/client/{token}/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  /**
   * Fetches products for a specific category for client view.
   *
   * @param categoryId The id of the category for which products are to be fetched.
   * @return List of ProductClientVO objects.
   */
  @GetMapping
  public List<ProductClientVO> findProducts(Integer categoryId) {
    return productService.listProducts(categoryId);
  }
}