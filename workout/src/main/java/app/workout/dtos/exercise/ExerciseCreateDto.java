package app.workout.dtos.exercise;


public record ExerciseCreateDto(
        Long workoutExerciseId,
        Integer reps,
        Double weight
) {
}
