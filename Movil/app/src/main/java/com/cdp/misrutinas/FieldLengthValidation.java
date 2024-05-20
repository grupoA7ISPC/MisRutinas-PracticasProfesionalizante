package com.cdp.misrutinas;

public class FieldLengthValidation {
    String field;
    int minLength;
    int maxLength;

    public FieldLengthValidation(String field, int minLength, int maxLength) {
        this.field = field;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
}