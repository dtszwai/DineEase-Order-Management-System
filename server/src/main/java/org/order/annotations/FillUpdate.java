package org.order.annotations;

import org.order.enums.FillType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark methods that require automatic filling of entity update details.
 * The value of this annotation determines the type of operation (INSERT or UPDATE) for which the
 * details should be filled.
 *
 * <p>When a method is annotated with {@code @FillUpdate(FillType.INSERT)}, both 'createdAt' and
 * 'createdBy' fields, as well as 'updatedAt' and 'updatedBy' fields of the entity will be filled.
 *
 * <p>When a method is annotated with {@code @FillUpdate(FillType.UPDATE)}, only 'updatedAt' and
 * 'updatedBy' fields of the entity will be filled.
 *
 * <p>This annotation should be used in conjunction with methods in the 'org.order.mappers'
 * package.
 *
 * @see org.order.enums.FillType
 * @see org.order.aspects.EntityUpdateAspect
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FillUpdate {

  /**
   * The type of operation (INSERT or UPDATE) for which the entity details should be filled. Default
   * value is FillType.UPDATE.
   *
   * @return the fill type
   */
  FillType value() default FillType.UPDATE;
}