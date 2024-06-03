package org.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * This exception is thrown when a requested resource is not found. It extends from the
 * {@code ResponseStatusException} class and sets the HTTP status to NOT_FOUND (404).
 */
public class NotFoundException extends ResponseStatusException {
  public NotFoundException() {
    super(HttpStatus.NOT_FOUND);
  }
}