package app.user.dtos.workout;

import app.user.enums.WorkoutType;

import java.util.UUID;

public record WorkoutCreateDto(
        String workoutName,
        String workoutDescription,
        WorkoutType workoutType
) {
}
