package app.user.dtos.workout;

public record WorkoutExerciseCreateDto(
        Long internalWorkoutId,
        String exerciseName
) {
}
