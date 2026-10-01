package app.user.dtos;

import app.user.enums.WorkoutType;

import java.util.UUID;

public record WorkoutCreateDto(
        UUID userId,
        String workoutName,
        String workoutDescription,
        WorkoutType workoutType
) {
}
