package app.workout.dtos.exercise;

import java.util.UUID;

public record ExerciseResponseDto(
        UUID externalWorkoutExerciseId,
        Long exerciseId,
        Integer reps,
        Double weight
) {
}
