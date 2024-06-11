package com.unimagdalena.Renta_Autos.exception;

public class NotFoundExceptionEntity extends Exception{
    public NotFoundExceptionEntity() {
        super();
    }

    public NotFoundExceptionEntity(String message) {
        super(message);
    }

    public NotFoundExceptionEntity(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundExceptionEntity(Throwable cause) {
        super(cause);
    }
}
