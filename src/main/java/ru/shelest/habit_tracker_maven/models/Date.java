package ru.shelest.habit_tracker_maven.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Date implements Serializable {

    private final int year;
    private final int month;
    private final int date;

    public LocalDate toLocalDate() {
        return LocalDate.of(this.year, this.month, this.date);
    }

    public static Date now() {
        return ofLocalDate(LocalDate.now());
    }

    public static Date ofLocalDate(final LocalDate localDate) {
        return new Date(
                localDate.getYear(),
                localDate.getMonthValue(),
                localDate.getDayOfMonth()
        );
    }
}
