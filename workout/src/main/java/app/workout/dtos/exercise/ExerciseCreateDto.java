package app.workout.dtos.exercise;


public record ExerciseCreateDto(
        Integer reps,
        Double weight
) {
}
