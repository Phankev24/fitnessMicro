package app.workout.dtos;

import app.workout.workout.WorkoutType;

import java.time.LocalDateTime;

public record WorkoutCreateDto(
        String workoutName,
        WorkoutType workoutType,
        LocalDateTime workoutDateTime
) {
}
