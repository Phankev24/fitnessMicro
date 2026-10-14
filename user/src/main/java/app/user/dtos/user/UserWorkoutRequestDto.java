package app.user.dtos.user;

import app.user.enums.WorkoutType;

public record UserWorkoutRequestDto(
        String workoutName,
        String workoutDescription,
        WorkoutType workoutType
) {
}
