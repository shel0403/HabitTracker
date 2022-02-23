package ru.shelest.habit_tracker_maven.exceptions;

public class NoSuchTrackerException extends RuntimeException {

    public NoSuchTrackerException() {
        super();
    }

    public NoSuchTrackerException(final String message) {
        super(message);
    }
}
