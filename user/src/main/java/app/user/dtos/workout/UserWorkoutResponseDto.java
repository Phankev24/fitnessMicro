package app.user.dtos.workout;

import app.user.dtos.user.UserResponseDto;
import java.util.List;

public record UserWorkoutResponseDto(
        UserResponseDto user,
        List<WorkoutResponseDto> workouts
) {
}
