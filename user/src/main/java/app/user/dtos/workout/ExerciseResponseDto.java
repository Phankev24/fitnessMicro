package app.user.dtos.workout;

import java.util.UUID;

public record ExerciseResponseDto(
        Long exerciseId,
        UUID externalWorkoutExerciseId,
        Integer reps,
        Double weight
) {
}
