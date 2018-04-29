package com.rbs.prime.api.controller.exception;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Encapsulates exception data.
 */
public final class ExceptionResponse {

    private final LocalDateTime date;
    private final String message;
    private final String details;

    /**
     * Exception information
     *
     * @param date The date and time exception occurred
     * @param message Message of the exception
     * @param details Details of the exception
     */
    public ExceptionResponse(final LocalDateTime date, final String message, final String details) {
        this.date = date;
        this.message = message;
        this.details = details;
    }

    public final LocalDateTime getDate() {
        return date;
    }

    public final String getMessage() {
        return message;
    }

    public final String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionResponse that = (ExceptionResponse) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(message, that.message) &&
                Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date, message, details);
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "date=" + date +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
