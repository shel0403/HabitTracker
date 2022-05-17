package ru.shelest.habit_tracker_maven.exceptions;

public class NoSuchDateException extends RuntimeException {

    public NoSuchDateException() {
        super();
    }

    public NoSuchDateException(final String message) {
        super(message);
    }
}
