package com.example.studentManagementSystem.exception;

/**
 * Custom exception used to represent a situation where a requested resource is not found.
 */
public class ResourceNotFoundedException extends RuntimeException {

    public ResourceNotFoundedException(String message) {
        super(message);
    }
}
