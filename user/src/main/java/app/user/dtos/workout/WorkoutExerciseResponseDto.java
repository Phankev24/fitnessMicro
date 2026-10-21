package app.user.dtos.workout;

import java.util.List;
import java.util.UUID;

public record WorkoutExerciseResponseDto(
        Long internalWorkoutExerciseId,
        UUID externalWorkoutExerciseId,
        UUID externalWorkoutId,
        String exerciseName,
        List<ExerciseResponseDto> sets
) {
}
