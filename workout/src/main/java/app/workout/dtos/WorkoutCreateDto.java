package app.workout.dtos;

import app.workout.enums.WorkoutType;

import java.util.List;
import java.util.UUID;

public record WorkoutCreateDto(
        UUID userId,
        String workoutName,
        String workoutDescription,
        WorkoutType workoutType
) {}
