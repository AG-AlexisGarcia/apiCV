package edu.altair.apicv.exceptions;

public class RecursoNotFoundException extends RuntimeException {
    public RecursoNotFoundException(String mensaje) {
        super(mensaje);
    }
}