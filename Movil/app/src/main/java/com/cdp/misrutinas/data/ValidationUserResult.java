package com.cdp.misrutinas.data;

public class ValidationUserResult {
    public boolean isValid;
    public String emailError;
    public String passwordError;
    public String nombreError;
    public String apellidoError;
    public String dniError;
    public String telError;

    public ValidationUserResult() {
        this.isValid = true;
    }
}
