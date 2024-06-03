package org.order.configs;

import lombok.extern.slf4j.Slf4j;
import org.order.interceptors.JwtAdminInterceptor;
import org.order.interceptors.ClientInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class is a configuration class that implements the WebMvcConfigurer interface. It is used to
 * add custom configurations to the Spring MVC framework. In this case, it is used to register the
 * JwtAdminInterceptor.
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
  /**
   * The JwtAdminInterceptor to be registered.
   */
  @Autowired
  private JwtAdminInterceptor jwtAdminInterceptor;

  @Autowired
  private ClientInterceptor clientInterceptor;

  /**
   * This method is used to add custom interceptors to the InterceptorRegistry. In this case, it is
   * used to register the JwtAdminInterceptor.
   *
   * @param registry The InterceptorRegistry to which the JwtAdminInterceptor is added.
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    log.info("Registering Jwt Admin interceptors...");
    registry.addInterceptor(jwtAdminInterceptor)
        .addPathPatterns("/admin/**")
        .excludePathPatterns("/admin/employees/login");

    log.info("Registering Jwt Client interceptors...");
    registry.addInterceptor(clientInterceptor)
        .addPathPatterns("/client/**")
        .excludePathPatterns("/client/entry");
  }

  /**
   * This method is used to add CORS mappings to the CorsRegistry. In this case, it is used to allow
   * all methods to be accessed from any origin.
   *
   * @param registry The CorsRegistry to which the CORS mappings are added.
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("*");
  }
}