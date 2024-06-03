package org.order.controllers.client;

import lombok.extern.slf4j.Slf4j;
import org.order.pojo.dto.OrderRequestDTO;
import org.order.pojo.vo.OrderClientVO;
import org.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * This controller class handles the endpoints related to order operations for clients. It is marked
 * as a @RestController to indicate that it is a controller class and that the methods within it
 * return response data to the client.
 */
@RestController("clientOrderController")
@RequestMapping("/client/{token}/orders")
@Slf4j
public class OrderController {

  @Autowired
  private OrderService orderService;

  /**
   * Submit an order.
   *
   * @param orderRequestDTO the order request containing all necessary information to submit an
   *                        order.
   * @return a ResponseEntity indicating the result of the operation.
   */
  @PostMapping("/submit")
  public ResponseEntity<String> submitOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
    orderService.submitOrder(orderRequestDTO);
    log.info("Received order: {}", orderRequestDTO);
    return ResponseEntity.ok("Order submitted successfully");
  }

  /**
   * Endpoint to get all orders for a specific customer.
   *
   * @return a list of OrderClientVO objects representing the orders of the customer.
   */
  @GetMapping
  public List<OrderClientVO> getAllOrders() {
    return orderService.listOrdersForClient();
  }
}