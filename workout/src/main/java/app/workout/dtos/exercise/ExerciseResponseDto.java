package app.workout.dtos.exercise;

public record ExerciseResponseDto(
        Long workoutExerciseId,
        Integer reps,
        Double weight
) {
}
