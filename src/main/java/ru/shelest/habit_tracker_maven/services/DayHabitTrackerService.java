package ru.shelest.habit_tracker_maven.services;

import org.springframework.stereotype.Service;
import ru.shelest.habit_tracker_maven.models.DayHabitTracker;

import java.time.LocalDate;
import java.util.List;

@Service
public interface DayHabitTrackerService {

    Boolean getWritingStatusByDate(LocalDate date);
    Boolean getListeningStatusByDate(LocalDate date);
    Boolean getSpeakingStatusByDate(LocalDate date);
    Boolean getReadingStatusByDate(LocalDate date);
    Boolean changeWritingStatusByDate(LocalDate date);
    Boolean changeListeningStatusByDate(LocalDate date);
    Boolean changeSpeakingStatusByDate(LocalDate date);
    Boolean changeReadingStatusByDate(LocalDate date);
    void save(final DayHabitTracker dayHabitTracker);
    DayHabitTracker getDayHabitTrackerByDate(LocalDate date);
    List<DayHabitTracker> fetchAll();
}
