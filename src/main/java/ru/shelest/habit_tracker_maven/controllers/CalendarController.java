package ru.shelest.habit_tracker_maven.controllers;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.shelest.habit_tracker_maven.exceptions.NoSuchDateException;
import ru.shelest.habit_tracker_maven.models.Date;
import ru.shelest.habit_tracker_maven.models.DayHabitTracker;
import ru.shelest.habit_tracker_maven.services.DayHabitTrackerService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class CalendarController {

    private static final int REPRESENTED_NUMBER_OF_WEEKS = 6;
    private static final int DAYS_IN_WEEK = 7;
    private static final int REPRESENTED_NUMBER_OF_DAYS = REPRESENTED_NUMBER_OF_WEEKS * DAYS_IN_WEEK;

    private final DayHabitTrackerService dayHabitTrackerService;

    @Autowired
    public CalendarController(DayHabitTrackerService dayHabitTrackerService) {
        this.dayHabitTrackerService = dayHabitTrackerService;
    }

    @PostMapping("/calendar/create")
    public List<DayHabitTracker> createCalendarFromStartOfWeekContainsDate(@NonNull @RequestBody Date date) {
        final Set<LocalDate> calendarDates = calendarDatesFromDate(date);

        calendarDates.forEach(day -> {
            try {
                this.dayHabitTrackerService.getDayHabitTrackerByDate(day);
            } catch (NoSuchDateException exception) {
                this.dayHabitTrackerService.save(new DayHabitTracker(day));
            }
        });

        return this.getCalendarFromStartOfWeekContainsDate(date);
    }

    @PostMapping("/calendar")
    public List<DayHabitTracker> getCalendarFromStartOfWeekContainsDate(@NonNull @RequestBody Date date) {
        final Set<LocalDate> calendarDates = calendarDatesFromDate(date);

        return this.dayHabitTrackerService.fetchAll().stream()
                .filter(tracker -> calendarDates.contains(tracker.getDate()))
                .collect(Collectors.toList());
    }

    private Set<LocalDate> calendarDatesFromDate(final Date date) {
        return Stream.iterate(fromStartOfWeek(date.toLocalDate()), d -> d.plusDays(1))
                .limit(REPRESENTED_NUMBER_OF_DAYS)
                .collect(Collectors.toSet());
    }

    private LocalDate fromStartOfWeek(final LocalDate date) {
        switch (date.getDayOfWeek()) {
            case MONDAY:
                return date;
            case TUESDAY:
                return date.minusDays(1);
            case WEDNESDAY:
                return date.minusDays(2);
            case THURSDAY:
                return date.minusDays(3);
            case FRIDAY:
                return date.minusDays(4);
            case SATURDAY:
                return date.minusDays(5);
            case SUNDAY:
                return date.minusDays(6);
            default:
                throw new IllegalArgumentException();
        }
    }
}
