package org.order.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;

/**
 * This class provides utility methods for creating and parsing JWT tokens.
 */
public class JwtUtil {
  /**
   * This method is used to create a JWT token.
   *
   * @param secretKey The secret key used for signing the token.
   * @param ttlMillis The time-to-live for the token in milliseconds.
   * @param claims    The claims to be included in the token.
   * @return The JWT token.
   */
  public static String createToken(String secretKey, long ttlMillis, Map<String, Object> claims) {
    SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    JwtBuilder builder = Jwts.builder().signWith(key);

    // Set each claim individually
    for (Map.Entry<String, Object> claim : claims.entrySet()) {
      builder = builder.claim(claim.getKey(), claim.getValue());
    }

    // Set the expiration date
    Instant exp = Instant.now().plusMillis(ttlMillis);
    builder.expiration(Date.from(exp));

    return builder.compact();
  }

  /**
   * This method is used to parse a JWT token.
   *
   * @param secretKey The secret key used for verifying the token.
   * @param token     The JWT token to be parsed.
   * @return The claims contained in the token.
   */
  public static Claims parseToken(String secretKey, String token) {
    SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload();

  }
}