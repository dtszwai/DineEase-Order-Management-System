package org.order.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * This class represents a customer token entity in the system. Each token is associated with a
 * customer. It also contains information about its expiry time, token, and whether it is expired.
 * Additionally, it keeps track of when each record was created and last updated, and by whom.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerToken {

  /**
   * The ID of the customer token.
   */
  private Long id;

  /**
   * The ID of the customer associated with the token.
   */
  private Long customerId;

  /**
   * The expiry time of the token. After this time, the token is considered expired and cannot be
   * used.
   */
  private LocalDateTime expiryTime;

  /**
   * The token associated with the customer. This token is a unique string that is used to identify
   * the customer.
   */
  private String token;

  /**
   * Indicates whether the token is expired. If true, the token is expired and cannot be used. If
   * false, the token is still valid and can be used.
   */
  private boolean isExpired;

  /**
   * The ID of the employee who created this record.
   */
  private Long createdBy;

  /**
   * The timestamp when this record was created.
   */
  private LocalDateTime createdAt;

  /**
   * The ID of the employee who last updated this record.
   */
  private Long updatedBy;

  /**
   * The timestamp when this record was last updated.
   */
  private LocalDateTime updatedAt;
}