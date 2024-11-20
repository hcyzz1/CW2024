package com.hcyzz1company.skybattle.exceptions;

/**
 * Thrown when a game level cannot be loaded properly,
 * such as due to missing classes or reflection issues.
 */
public class LevelLoadingException extends Exception {
    /**
     * Constructs a LevelLoadingException with a message and cause.
     *
     * @param message The error message.
     * @param cause   The cause of the exception (another {@link Throwable}).
     */
    public LevelLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
