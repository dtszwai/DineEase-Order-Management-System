package org.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * This exception is thrown when an account is locked due to too many failed login attempts. It
 * extends from the {@code ResponseStatusException} class and sets the HTTP status to LOCKED (423).
 */
public class AccountLockedException extends ResponseStatusException {

  public AccountLockedException() {
    super(HttpStatus.LOCKED);
  }
}