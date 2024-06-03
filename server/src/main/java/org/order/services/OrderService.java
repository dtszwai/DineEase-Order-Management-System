package org.order.services;

import org.order.exceptions.BadRequestException;
import org.order.constants.OrderStatus;
import org.order.mappers.OrderMapper;
import org.order.pojo.dto.OrderRequestDTO;
import org.order.pojo.dto.query.ProductQueryDTO;
import org.order.pojo.entity.Orders;
import org.order.pojo.entity.OrdersProduct;
import org.order.pojo.vo.OrderSummary;
import org.order.pojo.vo.OrderClientVO;
import org.order.utils.CustomerContextHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Service for managing orders.
 */
@Service
public class OrderService {

  private final OrderMapper orderMapper;
  private final ProductService productService;

  public OrderService(OrderMapper orderMapper, ProductService productService) {
    this.orderMapper = orderMapper;
    this.productService = productService;
  }

  /**
   * Submits a new order. This method will create a new order, insert it into the database, and
   * calculate the total price of the order based on the products included in the order. It will
   * then update the total price of the order in the database.
   *
   * @param orderRequestDTO the data transfer object containing the details of the order
   * @throws BadRequestException if a product in the order does not exist
   */
  @Transactional
  public void submitOrder(OrderRequestDTO orderRequestDTO) {
    // Create a new order
    Orders orders = new Orders();
    BeanUtils.copyProperties(orderRequestDTO, orders);
    orders.setStatus(OrderStatus.PROCESSING);
    orders.setCustomerId(CustomerContextHolder.getCustomerId());

    // Insert the order into the database
    orderMapper.insertOrder(orders);
    BigDecimal totalPrice = new BigDecimal(0);

    // Insert the products in the order into the database
    for (var orderedProductDTO : orderRequestDTO.getProducts()) {
      var product = new OrdersProduct();
      BeanUtils.copyProperties(orderedProductDTO, product);
      product.setOrdersId(orders.getId());

      // Get the product details
      var productQuery = new ProductQueryDTO();
      productQuery.setId(orderedProductDTO.getProductId());
      var products = productService.listProducts(productQuery);

      if (products.isEmpty()) {
        throw new BadRequestException();
      }

      // Calculate the total price of the product
      var productPrice = products.get(0).getPrice();
      product.setTotalPrice(productPrice.multiply(new BigDecimal(orderedProductDTO.getQuantity())));

      orderMapper.insertOrdersProduct(product);

      totalPrice = totalPrice.add(product.getTotalPrice());
    }
    // Update the total price of the order
    orders.setTotalPrice(totalPrice);
    orderMapper.updateOrder(orders);
  }

  /**
   * Retrieves a list of orders for the client currently in context. This method will get the
   * customer ID from the context, retrieve all orders for that customer, and for each order,
   * retrieve the products included in the order.
   *
   * @return a list of OrderClientVO objects representing the orders of the client
   */
  public List<OrderClientVO> listOrdersForClient() {
    // Get the customer ID from the context
    Long customerId = CustomerContextHolder.getCustomerId();
    List<OrderClientVO> orders = orderMapper.getAllOrders(customerId);
    orders.forEach(order -> order.setProducts(orderMapper.getOrderedProducts(order.getId())));
    return orders;
  }

  /**
   * Get a list of orders.
   *
   * @return a list of OrderSummary
   */
  public List<OrderSummary> listOrders() {
    return orderMapper.listOrders();
  }
}