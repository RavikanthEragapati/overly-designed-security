package com.overlydesigned.security.validation.custom;

import com.overlydesigned.security.validation.custom.impl.CheckOnlyOneIsNotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckOnlyOneIsNotNullValidator.class)
@Documented
public @interface CheckOnlyOneIsNotNull {

    String message() default "CheckOnlyOneIsNotNull validation failed";

    String[] fieldNames();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
