package org.order.controllers.admin;

import org.order.pojo.vo.OrderSummary;
import org.order.services.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controller for handling order related requests.
 */
@RestController
@RequestMapping("/admin/orders")
public class OrderController {

  private final OrderService orderService;

  /**
   * Constructor for OrderController.
   *
   * @param orderService the service to handle order operations
   */
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  /**
   * Get a list of orders.
   *
   * @return a list of OrderSummary
   */
  @GetMapping
  public List<OrderSummary> listOrders() {
    return orderService.listOrders();
  }
}