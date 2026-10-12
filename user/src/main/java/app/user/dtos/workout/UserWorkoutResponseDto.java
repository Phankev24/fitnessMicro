package app.user.dtos.workout;

import app.user.dtos.user.UserResponseDto;
import app.user.enums.WorkoutType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserWorkoutResponseDto(
        UserResponseDto user,
        List<WorkoutResponseDto> workouts
) {
}
