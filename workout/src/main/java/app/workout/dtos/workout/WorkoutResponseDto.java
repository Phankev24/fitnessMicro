package app.workout.dtos.workout;

import app.workout.dtos.workoutExercise.WorkoutExerciseResponseDto;
import app.workout.enums.WorkoutType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record WorkoutResponseDto(
        Long internalWorkoutId,
        UUID externalWorkoutId,
        UUID userId,
        String workoutName,
        String workoutDescription,
        LocalDateTime workoutDateTime,
        WorkoutType workoutType,
        List<WorkoutExerciseResponseDto> workoutSession
){}
