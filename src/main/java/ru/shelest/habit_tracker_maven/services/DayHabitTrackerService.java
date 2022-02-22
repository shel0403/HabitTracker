package ru.shelest.habit_tracker_maven.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
}
