package ru.shelest.habit_tracker_maven.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.shelest.habit_tracker_maven.exceptions.NoSuchDateException;
import ru.shelest.habit_tracker_maven.services.DayHabitTrackerService;

import java.time.LocalDate;

@RestController
public class DayHabitTrackerController {

    private static final String DEFAULT_HEADER_NAME = "Content-Type";
    private static final String DEFAULT_HEADER_VALUE = "application/json";
    private static final boolean REQUEST_BODY_IF_NOT_FOUND = false;

    private final DayHabitTrackerService dayHabitTrackerService;

    @Autowired
    public DayHabitTrackerController(DayHabitTrackerService dayHabitTrackerService) {
        this.dayHabitTrackerService = dayHabitTrackerService;
    }

    @PostMapping("/show/writing")
    public ResponseEntity<Boolean> getWritingStatusByDate(@RequestBody LocalDate date) {
        try {
            return ResponseEntity.ok()
                    .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                    .body(this.dayHabitTrackerService.getWritingStatusByDate(date));
        } catch (NoSuchDateException exception) {
            return ResponseEntity.ok()
                    .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                    .body(REQUEST_BODY_IF_NOT_FOUND);
        }
    }

    @PostMapping("/show/listening")
    public ResponseEntity<Boolean> getListeningStatusByDate(@RequestBody LocalDate date) {
        try {
            return ResponseEntity.ok()
                    .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                    .body(this.dayHabitTrackerService.getListeningStatusByDate(date));
        } catch (NoSuchDateException exception) {
            return ResponseEntity.ok()
                    .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                    .body(REQUEST_BODY_IF_NOT_FOUND);
        }
    }

    @PostMapping("/show/speaking")
    public ResponseEntity<Boolean> getSpeakingStatusByDate(@RequestBody LocalDate date) {
        try {
            return ResponseEntity.ok()
                    .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                    .body(this.dayHabitTrackerService.getSpeakingStatusByDate(date));
        } catch (NoSuchDateException exception) {
            return ResponseEntity.ok()
                    .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                    .body(REQUEST_BODY_IF_NOT_FOUND);
        }
    }

    @PostMapping("/show/reading")
    public ResponseEntity<Boolean> getReadingStatusByDate(@RequestBody LocalDate date) {
        try {
            return ResponseEntity.ok()
                    .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                    .body(this.dayHabitTrackerService.getReadingStatusByDate(date));
        } catch (NoSuchDateException exception) {
            return ResponseEntity.ok()
                    .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                    .body(REQUEST_BODY_IF_NOT_FOUND);
        }
    }

    @PostMapping("/change/writing")
    public ResponseEntity<Boolean> changeWritingStatusByDate(@RequestBody LocalDate date) {
        return ResponseEntity.ok()
                .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                .body(this.dayHabitTrackerService.changeWritingStatusByDate(date));
    }

    @PostMapping("/change/listening")
    public ResponseEntity<Boolean> changeListeningStatusByDate(@RequestBody LocalDate date) {
        return ResponseEntity.ok()
                .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                .body(this.dayHabitTrackerService.changeListeningStatusByDate(date));
    }

    @PostMapping("/change/speaking")
    public ResponseEntity<Boolean> changeSpeakingStatusByDate(@RequestBody LocalDate date) {
        return ResponseEntity.ok()
                .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                .body(this.dayHabitTrackerService.changeSpeakingStatusByDate(date));
    }

    @PostMapping("/change/reading")
    public ResponseEntity<Boolean> changeReadingStatusByDate(@RequestBody LocalDate date) {
        return ResponseEntity.ok()
                .header(DEFAULT_HEADER_NAME, DEFAULT_HEADER_VALUE)
                .body(this.dayHabitTrackerService.changeReadingStatusByDate(date));
    }
}
