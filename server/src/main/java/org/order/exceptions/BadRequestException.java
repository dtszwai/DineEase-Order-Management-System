package org.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * This exception is thrown when a request is malformed. It extends from the
 * {@code ResponseStatusException} class and sets the HTTP status to BAD_REQUEST (400).
 */
public class BadRequestException extends ResponseStatusException {
  public BadRequestException() {
    super(HttpStatus.BAD_REQUEST);
  }
}