package org.order.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.order.annotations.FillUpdate;
import org.order.enums.FillType;
import org.order.utils.EmployeeContextHolder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * Aspect for handling entity update details. This aspect intercepts methods annotated with
 * {@code @FillUpdate} and fills in the necessary fields.
 */
@Aspect
@Component
@Slf4j
public class EntityUpdateAspect {
  private static final String SET_CREATED_AT = "setCreatedAt";
  private static final String SET_CREATED_BY = "setCreatedBy";
  private static final String SET_UPDATED_AT = "setUpdatedAt";
  private static final String SET_UPDATED_BY = "setUpdatedBy";

  /**
   * Pointcut for methods annotated with @FillUpdate in the org.order.mappers package.
   */
  @Pointcut("execution(* org.order.mappers.*.*(..)) && @annotation(org.order.annotations.FillUpdate)")
  public void entityUpdatePointCut() {

  }

  /**
   * Advice that runs before methods matching the entityUpdatePointCut pointcut. This method fills
   * in the necessary fields for the entity being updated or inserted.
   *
   * @param joinPoint the join point at which the advice is applied
   * @throws Exception if there is an error while filling in the fields
   */
  @Before("entityUpdatePointCut()")
  public void fillEntityUpdateDetails(JoinPoint joinPoint) throws Exception {
    // Get the annotation value from the method signature
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    FillUpdate annotation = signature.getMethod().getAnnotation(FillUpdate.class);
    FillType type = annotation.value();

    // Get the entity object from the method arguments
    Object[] args = joinPoint.getArgs();
    if (args == null || args.length == 0) {
      return; // No entity to update
    }
    Object entity = args[0];

    // Fill in the necessary fields based on the update type
    LocalDateTime now = LocalDateTime.now();
    Long employeeId = EmployeeContextHolder.getEmployeeId();

    // Use reflection to set the fields on the entity object
    try {
      if (type == FillType.INSERT) {
        entity.getClass().getMethod(SET_CREATED_AT, LocalDateTime.class).invoke(entity, now);
        entity.getClass().getMethod(SET_CREATED_BY, Long.class).invoke(entity, employeeId);
        entity.getClass().getMethod(SET_UPDATED_AT, LocalDateTime.class).invoke(entity, now);
        entity.getClass().getMethod(SET_UPDATED_BY, Long.class).invoke(entity, employeeId);
      }
      if (type == FillType.UPDATE) {
        entity.getClass().getMethod(SET_UPDATED_AT, LocalDateTime.class).invoke(entity, now);
        entity.getClass().getMethod(SET_UPDATED_BY, Long.class).invoke(entity, employeeId);
      }
    } catch (Exception e) {
      log.error("Error while filling data", e);
      throw new Exception("Error while filling data", e);
    }
  }
}