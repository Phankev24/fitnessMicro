package app.user.dtos;

import app.user.enums.WorkoutType;

public record UserWorkoutRequestDto(
        String workoutName,
        String workoutDescription,
        WorkoutType workoutType
) {
}
