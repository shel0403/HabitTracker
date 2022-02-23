package ru.shelest.habit_tracker_maven.utils;

import java.util.function.Supplier;

public final class ConditionCheck {

    private ConditionCheck() {
    }

    public static void check(boolean condition) {
        check(condition, () -> "");
    }

    public static void check(boolean condition, final Supplier<String> exceptionMessageSupplier) {
        if (!condition) {
            throw new IllegalStateException(exceptionMessageSupplier.get());
        }
    }

    public static void requireNonNull(final Object object) {
        requireNonNull(object, () -> "");
    }

    public static void requireNonNull(final Object object, final Supplier<String> exceptionMessageSupplier) {
        require(object != null, exceptionMessageSupplier);
    }

    public static void require(boolean condition) {
        require(condition, () -> "");
    }

    public static void require(boolean condition, final Supplier<String> exceptionMessageSupplier) {
        if (!condition) {
            throw new IllegalArgumentException(exceptionMessageSupplier.get());
        }
    }
}
