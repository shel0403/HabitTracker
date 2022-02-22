package ru.shelest.habit_tracker_maven.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shelest.habit_tracker_maven.exceptions.NoSuchDateException;
import ru.shelest.habit_tracker_maven.models.DayHabitTracker;
import ru.shelest.habit_tracker_maven.repositories.DayHabitTrackerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DayHabitTrackerServiceImpl implements DayHabitTrackerService {

    private final DayHabitTrackerRepository repository;

    @Autowired
    public DayHabitTrackerServiceImpl(DayHabitTrackerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean getWritingStatusByDate(LocalDate date) {
        return getDayHabitTrackerByDate(date).getWriting();
    }

    @Override
    public Boolean getListeningStatusByDate(LocalDate date) {
        return getDayHabitTrackerByDate(date).getListening();
    }

    @Override
    public Boolean getSpeakingStatusByDate(LocalDate date) {
        return getDayHabitTrackerByDate(date).getSpeaking();
    }

    @Override
    public Boolean getReadingStatusByDate(LocalDate date) {
        return getDayHabitTrackerByDate(date).getReading();
    }

    @Override
    public Boolean changeWritingStatusByDate(LocalDate date) {
        final var trackers = getDayHabitsTrackersByDate(date);

        final DayHabitTracker tracker = trackers.isEmpty() ?
                new DayHabitTracker(date) : trackers.get(0);

        final var result = tracker.changeWritingStatus();
        this.repository.save(tracker);

        return result;
    }

    @Override
    public Boolean changeListeningStatusByDate(LocalDate date) {
        final var trackers = getDayHabitsTrackersByDate(date);

        final DayHabitTracker tracker = trackers.isEmpty() ?
                new DayHabitTracker(date) : trackers.get(0);

        final var result = tracker.changeListeningStatus();
        this.repository.save(tracker);

        return result;
    }

    @Override
    public Boolean changeSpeakingStatusByDate(LocalDate date) {
        final var trackers = getDayHabitsTrackersByDate(date);

        final DayHabitTracker tracker = trackers.isEmpty() ?
                new DayHabitTracker(date) : trackers.get(0);

        final var result = tracker.changeSpeakingStatus();
        this.repository.save(tracker);

        return result;
    }

    @Override
    public Boolean changeReadingStatusByDate(LocalDate date) {
        final var trackers = getDayHabitsTrackersByDate(date);

        final DayHabitTracker tracker = trackers.isEmpty() ?
                new DayHabitTracker(date) : trackers.get(0);

        final var result = tracker.changeReadingStatus();
        this.repository.save(tracker);

        return result;
    }

    private DayHabitTracker getDayHabitTrackerByDate(final LocalDate date) {
        final List<DayHabitTracker> habitTrackers = getDayHabitsTrackersByDate(date);

        if (habitTrackers.isEmpty()) {
            throw new NoSuchDateException();
        }

        return habitTrackers.get(0);
    }

    private List<DayHabitTracker> getDayHabitsTrackersByDate(final LocalDate date) {
        return this.fetchAll().stream()
                .filter(tracker -> tracker.getDate().equals(date))
                .limit(1)
                .collect(Collectors.toList());
    }

    private List<DayHabitTracker> fetchAll() {
        return repository.findAll();
    }
}
