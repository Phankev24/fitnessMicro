package app.workout.dtos.exercise;

public record ExerciseResponseDto(
        Long workoutExerciseId,
        Long exerciseId,
        Integer reps,
        Double weight
) {
}
