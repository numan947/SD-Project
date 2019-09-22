package com.example.exception;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Exception that could be generated in the domain layer.
 **/

public interface ErrorBundle {

    Exception getException();

    String getErrorMessage();
}
