package app.workout.dtos;

import app.workout.enums.WorkoutType;

import java.time.LocalDateTime;

public record WorkoutUpdateDto(
        String workoutName,
        String workoutDescription,
        WorkoutType workoutType,
        LocalDateTime workoutDateTime
) {
}
