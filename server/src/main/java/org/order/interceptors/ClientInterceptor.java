package org.order.interceptors;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.order.exceptions.UnauthorizedException;
import org.order.pojo.entity.CustomerToken;
import org.order.services.CustomerTokenService;
import org.order.utils.CustomerContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@NonNullApi
public class ClientInterceptor implements HandlerInterceptor {

  private final CustomerTokenService customerTokenService;

  public ClientInterceptor(CustomerTokenService customerTokenService) {
    this.customerTokenService = customerTokenService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    // If the handler is not a method in a controller, return true.
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }

    // Get the request URI.
    String requestURI = request.getRequestURI();
    CustomerToken cusToken;

    // Retrieve the token from the request URI and find the token in the database.
    String token = requestURI.split("/")[2];
    cusToken = customerTokenService.getToken(token);

    // If the token is not found or is expired, throw an UnauthorizedException.
    if (cusToken == null || cusToken.isExpired()) {
      throw new UnauthorizedException();
    }

    // Set the customer ID in the CustomerContextHolder.
    CustomerContextHolder.setCustomerId(cusToken.getCustomerId());

    return true;
  }
}