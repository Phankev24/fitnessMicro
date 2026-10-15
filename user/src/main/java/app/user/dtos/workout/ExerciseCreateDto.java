package app.user.dtos.workout;

public record ExerciseCreateDto(
        Long workoutExerciseId,
        Integer reps,
        Double weight
) {
}
