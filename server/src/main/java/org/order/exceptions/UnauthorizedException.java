package org.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * This exception is thrown when a request is unauthorized. It extends from the
 * {@code ResponseStatusException} class and sets the HTTP status to UNAUTHORIZED (401).
 */
public class UnauthorizedException extends ResponseStatusException {
  public UnauthorizedException() {
    super(HttpStatus.UNAUTHORIZED);
  }
}