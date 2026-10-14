package app.user.dtos.workout;

import java.util.List;

public record WorkoutExerciseResponseDto(
        Long workoutExerciseId,
        String exerciseName,
        List<ExerciseResponseDto> sets
) {
}
