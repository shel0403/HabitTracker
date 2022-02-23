package ru.shelest.habit_tracker_maven.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Date {

    private final int year;
    private final int month;
    private final int date;

    public LocalDate toLocalDate() {
        return LocalDate.of(this.year, this.month, this.date);
    }
}
