package ru.shelest.habit_tracker_maven.utils;

import java.util.function.Supplier;

public final class ConditionCheck {

    private ConditionCheck() {
    }

    public static void requireNonNull(
            final Object object,
            final Supplier<RuntimeException> exceptionSupplier
    ) {
        require(object != null, exceptionSupplier);
    }

    public static void require(
            boolean condition,
            final Supplier<RuntimeException> exceptionSupplier
    ) {
        if (!condition) {
            throw exceptionSupplier.get();
        }
    }
}
