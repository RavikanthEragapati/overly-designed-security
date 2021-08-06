package com.overlydesigned.security.validation.custom.impl;

import com.overlydesigned.security.model.request.CustomRequest;
import com.overlydesigned.security.validation.custom.CheckOnlyOneIsNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckOnlyOneIsNotNullValidatorTest {

    @Mock
    ConstraintValidatorContext context;

    @Mock
    CheckOnlyOneIsNotNull hasOnlyOneValue;

    @Spy
    CheckOnlyOneIsNotNullValidator hasOnlyOneValueValidator;

    @Test
    public void isValid_shouldReturnFalse_whenBothAppIdAndAppGroupAreGiven() {
        when(hasOnlyOneValue.fieldNames()).thenReturn(new String[]{"abc", "xyz"});
        hasOnlyOneValueValidator.initialize(hasOnlyOneValue);

        CustomRequest request = CustomRequest.builder()
                .abc("abc")
                .xyz("xyz")
                .build();
        assertFalse(hasOnlyOneValueValidator.isValid(request, context));
    }

    @Test
    public void isValid_shouldReturnFalse_whenBothAppIdAndAppGroupAreNotGiven() {
        when(hasOnlyOneValue.fieldNames()).thenReturn(new String[]{"abc", "xyz"});
        hasOnlyOneValueValidator.initialize(hasOnlyOneValue);

        CustomRequest request = CustomRequest.builder()
                .build();
        assertFalse(hasOnlyOneValueValidator.isValid(request, context));
    }

    @Test
    public void isValid_shouldReturnTrue_whenOnlyAppGroupIsGiven() {
        when(hasOnlyOneValue.fieldNames()).thenReturn(new String[]{"abc", "xyz"});
        hasOnlyOneValueValidator.initialize(hasOnlyOneValue);

        CustomRequest request = CustomRequest.builder()
                .abc("abc")
                .build();
        assertTrue(hasOnlyOneValueValidator.isValid(request, context));
    }

    @Test
    public void isValid_shouldReturnTrue_whenOnlyAppIdIsGiven() {
        when(hasOnlyOneValue.fieldNames()).thenReturn(new String[]{"abc", "xyz"});
        hasOnlyOneValueValidator.initialize(hasOnlyOneValue);

        CustomRequest request = CustomRequest.builder()
                .xyz("xyz")
                .build();
        assertTrue(hasOnlyOneValueValidator.isValid(request, context));
    }

    @Test
    public void isValid_shouldThrowException_whenInvalidFieldIsGiven() {
        when(hasOnlyOneValue.fieldNames()).thenReturn(new String[]{"InvalidField", "xyz"});
        hasOnlyOneValueValidator.initialize(hasOnlyOneValue);

        CustomRequest request = CustomRequest.builder()
                .abc("hky")
                .xyz("xyz")
                .build();
        assertThrows(NullPointerException.class, () -> hasOnlyOneValueValidator.isValid(request, context));
    }

}
