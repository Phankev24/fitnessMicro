package app.user.dtos;

import app.user.enums.WorkoutType;

public record UserWorkoutRequestDto(
        String workoutName,
        WorkoutType workoutType
) {
}
