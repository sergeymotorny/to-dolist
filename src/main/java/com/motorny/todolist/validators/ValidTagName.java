package com.motorny.todolist.validators;

import com.motorny.todolist.validators.classes.TagNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotBlank
@Size(min = 3, max = 50)
@Constraint(validatedBy = TagNameValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTagName {

    String message() default "Invalid tag name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
