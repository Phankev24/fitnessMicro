package app.workout.dtos;

import app.workout.workout.WorkoutType;

import java.time.LocalDateTime;
import java.util.UUID;

public record WorkoutResponseDto(
        Long internalWorkoutId,
        UUID externalWorkoutId,
        String workoutName,
        LocalDateTime workoutDateTime,
        WorkoutType workoutType
){}
