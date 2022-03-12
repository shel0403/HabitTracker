package ru.shelest.habit_tracker_maven.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "DayHabitTracker")
@Table(
        name = "day_habit_tracker",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "date_unique",
                        columnNames = "executed_date"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class DayHabitTracker implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "writing", nullable = false)
    private Boolean writing;

    @Column(name = "listening", nullable = false)
    private Boolean listening;

    @Column(name = "speaking", nullable = false)
    private Boolean speaking;

    @Column(name = "reading", nullable = false)
    private Boolean reading;

    @Column(name = "executed_date", nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    public DayHabitTracker(
            Boolean writing,
            Boolean listening,
            Boolean speaking,
            Boolean reading,
            LocalDate date
    ) {
        this.writing = writing;
        this.listening = listening;
        this.speaking = speaking;
        this.reading = reading;
        this.date = date;
    }

    public DayHabitTracker(LocalDate date) {
        this(false, false, false, false, date);
    }

    public Boolean changeWritingStatus() {
        this.writing = !this.writing;

        return this.writing;
    }

    public Boolean changeListeningStatus() {
        this.listening = !this.listening;

        return this.listening;
    }

    public Boolean changeSpeakingStatus() {
        this.speaking = !this.speaking;

        return this.speaking;
    }

    public Boolean changeReadingStatus() {
        this.reading = !this.reading;

        return this.reading;
    }

    @Override
    public String toString() {
        return "DayHabitTracker{" + "id=" + id +
                ", writing=" + writing +
                ", listening=" + listening +
                ", speaking=" + speaking +
                ", reading=" + reading +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayHabitTracker that = (DayHabitTracker) o;
        return getId().equals(that.getId()) &&
                getWriting().equals(that.getWriting()) &&
                getListening().equals(that.getListening()) &&
                getSpeaking().equals(that.getSpeaking()) &&
                getReading().equals(that.getReading()) &&
                getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getWriting(),
                getListening(),
                getSpeaking(),
                getReading(),
                getDate()
        );
    }
}
