package ru.shelest.habit_tracker_maven.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shelest.habit_tracker_maven.exceptions.NoSuchDateException;
import ru.shelest.habit_tracker_maven.exceptions.NoSuchTrackerException;
import ru.shelest.habit_tracker_maven.models.DayHabitTracker;
import ru.shelest.habit_tracker_maven.repositories.DayHabitTrackerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ru.shelest.habit_tracker_maven.utils.ConditionCheck.requireNonNull;

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

        if (trackers.isEmpty()) {
            final var toChange = new DayHabitTracker(date);
            toChange.changeWritingStatus();
            this.save(toChange);

            return toChange.getWriting();
        }

        final var toChange = trackers.get(0);
        toChange.changeWritingStatus();
        this.update(toChange, toChange.getId());

        return toChange.getWriting();
    }

    @Override
    public Boolean changeListeningStatusByDate(LocalDate date) {
        final var trackers = getDayHabitsTrackersByDate(date);

        if (trackers.isEmpty()) {
            final var toChange = new DayHabitTracker(date);
            toChange.changeListeningStatus();
            this.save(toChange);

            return toChange.getListening();
        }

        final var toChange = trackers.get(0);
        toChange.changeListeningStatus();
        this.update(toChange, toChange.getId());

        return toChange.getListening();
    }

    @Override
    public Boolean changeSpeakingStatusByDate(LocalDate date) {
        final var trackers = getDayHabitsTrackersByDate(date);

        if (trackers.isEmpty()) {
            final var toChange = new DayHabitTracker(date);
            toChange.changeSpeakingStatus();
            this.save(toChange);

            return toChange.getSpeaking();
        }

        final var toChange = trackers.get(0);
        toChange.changeSpeakingStatus();
        this.update(toChange, toChange.getId());

        return toChange.getSpeaking();
    }

    @Override
    public Boolean changeReadingStatusByDate(LocalDate date) {
        final var trackers = getDayHabitsTrackersByDate(date);

        if (trackers.isEmpty()) {
            final var toChange = new DayHabitTracker(date);
            toChange.changeReadingStatus();
            this.save(toChange);

            return toChange.getReading();
        }

        final var toChange = trackers.get(0);
        toChange.changeReadingStatus();
        this.update(toChange, toChange.getId());

        return toChange.getReading();
    }

    @Override
    public void save(final DayHabitTracker dayHabitTracker) {
        repository.save(dayHabitTracker);
    }

    @Override
    public DayHabitTracker getDayHabitTrackerByDate(final LocalDate date) {
        final List<DayHabitTracker> habitTrackers = getDayHabitsTrackersByDate(date);

        if (habitTrackers.isEmpty()) {
            throw new NoSuchDateException();
        }

        return habitTrackers.get(0);
    }

    public List<DayHabitTracker> getDayHabitsTrackersByDate(final LocalDate date) {
        return this.fetchAll().stream()
                .filter(tracker -> tracker.getDate().equals(date))
                .limit(1)
                .collect(Collectors.toList());
    }

    @Override
    public List<DayHabitTracker> fetchAll() {
        return repository.findAll();
    }

    public DayHabitTracker update(final DayHabitTracker dayHabitTracker, final Long id) {
        final var toUpdate = repository.findById(id)
                .orElseThrow(NoSuchTrackerException::new);

        final var writing = dayHabitTracker.getWriting();
        final var listening = dayHabitTracker.getListening();
        final var speaking = dayHabitTracker.getSpeaking();
        final var reading = dayHabitTracker.getReading();
        final var date = dayHabitTracker.getDate();

        final var requireNonNullMessage = "Value is null!";

        requireNonNull(writing, () -> new IllegalArgumentException(requireNonNullMessage));
        requireNonNull(listening, () -> new IllegalArgumentException(requireNonNullMessage));
        requireNonNull(speaking, () -> new IllegalArgumentException(requireNonNullMessage));
        requireNonNull(reading, () -> new IllegalArgumentException(requireNonNullMessage));
        requireNonNull(date, () -> new IllegalArgumentException(requireNonNullMessage));

        toUpdate.setWriting(writing);
        toUpdate.setListening(listening);
        toUpdate.setSpeaking(speaking);
        toUpdate.setReading(reading);
        toUpdate.setDate(date);

        return repository.save(toUpdate);
    }

    public void deleteById(final Long id) {
        repository.deleteById(id);
    }
}
