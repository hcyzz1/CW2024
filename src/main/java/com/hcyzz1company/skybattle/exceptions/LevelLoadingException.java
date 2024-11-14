package com.hcyzz1company.skybattle.exceptions;

/**
 * Custom exception class while loading a game level.
 * This exception is thrown when a level cannot be properly loaded due to various reasons,
 * such as missing classes, incorrect constructors, or reflection issues.
 *
 * <p>It extends the {@link Exception} class to provide a specific exception for level loading errors.</p>
 *
 * <p>Example usage:
 * <pre>
 * try {
 * } catch (LevelLoadingException e) {
 *     // Handle the error
 *     System.out.println("Error: " + e.getMessage());
 * }
 * </pre>
 * </p>
 */
public class LevelLoadingException extends Exception {
    /**
     * Constructor for the LevelLoadingException class.
     * This constructor accepts a message and a cause for the exception.
     *
     * @param message The detail message explaining the cause of the exception.
     * @param cause   The cause of the exception (a {@link Throwable} object).
     *                This allows wrapping another exception that led to the level loading failure.
     */
    public LevelLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
