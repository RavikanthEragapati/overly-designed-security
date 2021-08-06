package com.overlydesigned.security.validation.custom.impl;

import com.overlydesigned.security.validation.custom.CheckOnlyOneIsNotNull;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

public class CheckOnlyOneIsNotNullValidator implements ConstraintValidator<CheckOnlyOneIsNotNull, Object> {

    private String[] fieldNames;

    @Override
    public void initialize(CheckOnlyOneIsNotNull constraintAnnotation) {
        this.fieldNames = constraintAnnotation.fieldNames();
    }

    @Override
    public boolean isValid(Object objectToValidate, ConstraintValidatorContext context) {
        boolean foundOne = false;
        Field classField = null;
        try {
            for (String field : fieldNames) {
                classField = ReflectionUtils.findField(objectToValidate.getClass(), field);
                classField.setAccessible(true);
                if (!Objects.isNull(classField.get(objectToValidate))) {
                    if (foundOne)
                        return false;
                    else
                        foundOne = true;
                }
            }
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        }

        return foundOne;
    }
}