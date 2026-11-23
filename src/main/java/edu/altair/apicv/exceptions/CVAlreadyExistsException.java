package edu.altair.apicv.exceptions;

public class CVAlreadyExistsException extends RuntimeException {
    public CVAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}