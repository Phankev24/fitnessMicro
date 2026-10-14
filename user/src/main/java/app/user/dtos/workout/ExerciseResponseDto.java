package app.user.dtos.workout;

public record ExerciseResponseDto(
        Long workoutExerciseId,
        Integer reps,
        Double weight
) {
}
