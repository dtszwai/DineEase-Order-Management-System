package org.order.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.order.annotations.FillUpdate;
import org.order.enums.FillType;
import org.order.pojo.entity.CustomerToken;

/**
 * This mapper interface provides methods to interact with the 'customer_token' table.
 */
@Mapper
public interface CustomerTokenMapper {

  /**
   * Finds a customer token by its token.
   *
   * @param token the token of the customer token to find.
   * @return the customer token with the given token.
   */
  @Select("SELECT * FROM customer_token WHERE token = #{token}")
  CustomerToken findByToken(String token);

  /**
   * Inserts a new customer token into the 'customer_token' table in the database.
   *
   * @param customerToken the customer token to insert into the database.
   */
  @Insert("INSERT INTO customer_token (token, customer_id, created_by, created_at, updated_by, updated_at) VALUES (#{token}, #{customerId}, #{createdBy}, #{createdAt},#{updatedBy}, #{updatedAt})")
  @FillUpdate(FillType.INSERT)
  void insert(CustomerToken customerToken);

  /**
   * Finds a customer token by the customer it belongs to.
   *
   * @param customerId the customer to find the customer token for.
   * @return the customer token for the given customer.
   */
  @Select("SELECT * FROM customer_token WHERE customer_id = #{customerId}")
  CustomerToken findToken(Long customerId);

  @FillUpdate(FillType.UPDATE)
  void update(CustomerToken customerToken);
}