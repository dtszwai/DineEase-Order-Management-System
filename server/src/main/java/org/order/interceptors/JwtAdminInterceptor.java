package org.order.interceptors;

import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.order.exceptions.UnauthorizedException;
import org.order.constants.JwtClaimsConstant;
import org.order.properties.JwtProperties;
import org.order.utils.EmployeeContextHolder;
import org.order.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * This class is an interceptor that checks for valid JWT tokens in the Authorization header of
 * incoming requests. If the token is valid, it extracts the user ID from the token and stores it in
 * a ThreadLocal for later use. If the token is not valid, it throws an UnauthorizedException.
 */
@Component
@Slf4j
@NonNullApi
public class JwtAdminInterceptor implements HandlerInterceptor {

  private final JwtProperties jwtProperties;

  /**
   * Constructs a new JwtAdminInterceptor with the provided {@code JwtProperties}.
   *
   * @param jwtProperties The properties of the JWT token.
   */
  public JwtAdminInterceptor(JwtProperties jwtProperties) {
    this.jwtProperties = jwtProperties;
  }

  /**
   * This method is called before the actual handler is executed. It checks for a valid JWT token in
   * the Authorization header of the request. If the token is valid, it extracts the user ID from
   * the token and stores it in a ThreadLocal. If the token is not valid, it throws an
   * UnauthorizedException.
   *
   * @param request  The incoming request.
   * @param response The outgoing response.
   * @param handler  The handler that is to be executed.
   * @return true if the execution chain should proceed with the next interceptor or the handler
   * itself.
   * @throws UnauthorizedException if the JWT token is not valid.
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    // If the handler is not a method in a controller, return true.
    if (!(handler instanceof HandlerMethod handlerMethod)) {
      return true;
    }

    String token = request.getHeader(jwtProperties.getHeaderName());

    // Get the class name and method name of the handler.
    String className = handlerMethod.getBeanType().getSimpleName();
    String methodName = handlerMethod.getMethod().getName();
    String fullHandlerName = className + "." + methodName;

    try {
      Claims claims = JwtUtil.parseToken(jwtProperties.getAdminSecretKey(), token);
      Long employeeId = Long.valueOf(claims.get(JwtClaimsConstant.CLAIM_KEY_USER_ID).toString());
      EmployeeContextHolder.setEmployeeId(employeeId);
      log.info("{} called by Employee ID: {}", fullHandlerName, employeeId);
      return true;
    } catch (Exception ex) {
      log.error("Unauthorized access: {}, token: {}", fullHandlerName, token);
      throw new UnauthorizedException();
    }
  }
}