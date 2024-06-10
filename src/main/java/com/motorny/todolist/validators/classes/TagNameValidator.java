package com.motorny.todolist.validators.classes;

import com.motorny.todolist.validators.ValidTagName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;


public class TagNameValidator implements ConstraintValidator<ValidTagName, String> {

    private static final String SPECIAL_CHARACTERS = "!@$%^&*()-+";
    private static final Pattern SPECIAL_CHAR_PATTERN =
            Pattern.compile("[" + Pattern.quote(SPECIAL_CHARACTERS) + "]");

    @Override
    public void initialize(ValidTagName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String tag, ConstraintValidatorContext context) {

        if (tag == null || tag.trim().isEmpty()) {
            return false;
        }

        if (!SPECIAL_CHAR_PATTERN.matcher(tag).find()) {
            return false;
        }

        int length = tag.length();

        return length >= 3 && length <= 50;
    }
}
