package com.motorny.todolist.validators;

import com.motorny.todolist.validators.classes.PasswordConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Documented
@Size(min = 8, max = 30)
@NotBlank
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConstraintValidator.class)
public @interface ValidPassword {
    String message() default "Invalid password!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
