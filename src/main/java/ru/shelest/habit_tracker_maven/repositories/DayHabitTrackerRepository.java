package ru.shelest.habit_tracker_maven.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shelest.habit_tracker_maven.models.DayHabitTracker;

@Repository
public interface DayHabitTrackerRepository extends JpaRepository<DayHabitTracker, Long> {
}
