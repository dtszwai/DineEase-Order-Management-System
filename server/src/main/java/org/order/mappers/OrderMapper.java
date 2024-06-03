package org.order.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.order.pojo.entity.Orders;
import org.order.pojo.entity.OrdersProduct;
import org.order.pojo.vo.OrderSummary;
import org.order.pojo.vo.OrderClientVO;
import org.order.pojo.vo.OrderedProductVO;
import java.util.List;

/**
 * This interface represents the mapper for Order operations. It provides methods to interact with
 * the database for Order related operations. It uses MyBatis annotations to map SQL queries to Java
 * methods.
 */
@Mapper
public interface OrderMapper {

  /**
   * Inserts an order into the database.
   *
   * @param orders The order request object to insert.
   */
  @Insert("INSERT INTO orders (customer_id) VALUES (#{customerId})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertOrder(Orders orders);

  /**
   * Inserts a ordered_product into the ordered_product table in the database.
   *
   * @param ordersProduct The product order data transfer object to insert.
   */
  @Insert("INSERT INTO orders_product (orders_id, product_id, quantity, total_price) VALUES (#{ordersId}, #{productId}, #{quantity}, #{totalPrice})")
  void insertOrdersProduct(OrdersProduct ordersProduct);

  /**
   * Retrieves all orders for a specific customer from the database.
   *
   * @param customerId The ID of the customer.
   * @return A list of OrderClientVO objects representing the orders of the customer.
   */
  @Select("SELECT * FROM orders WHERE customer_id = #{customerId}")
  List<OrderClientVO> getAllOrders(Long customerId);

  /**
   * Retrieves all products for a specific order from the database.
   *
   * @param ordersId The ID of the order.
   * @return A list of OrderedProductVO objects representing the products of the order.
   */
  @Select("SELECT name, description, image, quantity, total_price FROM orders_product JOIN product ON product_id = product.id WHERE orders_id = #{ordersId};")
  List<OrderedProductVO> getOrderedProducts(Long ordersId);

  /**
   * Updates an order in the database.
   *
   * @param orders The order object to update.
   */
  void updateOrder(Orders orders);

  /**
   * Retrieves all orders from the database.
   *
   * @return A list of OrderSummary objects representing the orders.
   */
  List<OrderSummary> listOrders();
}