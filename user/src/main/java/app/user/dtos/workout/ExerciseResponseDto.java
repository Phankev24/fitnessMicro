package app.user.dtos.workout;

public record ExerciseResponseDto(
        Long workoutExerciseId,
        Long exerciseId,
        Integer reps,
        Double weight
) {
}
