package org.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This class holds the properties for JWT tokens. It includes the secret keys and time-to-live
 * (TTL) values for admin.
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

  /**
   * The secret key for admin JWT tokens.
   */
  private String adminSecretKey;

  /**
   * The time-to-live (TTL) for admin JWT tokens, in milliseconds.
   */
  private long adminTtl;

  /**
   * The name of the header that holds the admin JWT token.
   */
  private String headerName;
}