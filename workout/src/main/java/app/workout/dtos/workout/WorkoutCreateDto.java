package app.workout.dtos.workout;

import app.workout.enums.WorkoutType;

import java.util.UUID;

public record WorkoutCreateDto(
        String workoutName,
        String workoutDescription,
        WorkoutType workoutType
) {}
