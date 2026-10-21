package app.workout.dtos.exercise;

import app.workout.entities.Exercise;
import app.workout.entities.WorkoutExercise;
import app.workout.repository.WorkoutExerciseRepository;
import org.springframework.stereotype.Component;

@Component
public class ExerciseCreateMapper {

    private final WorkoutExerciseRepository workoutExerciseRepository;

    public ExerciseCreateMapper(WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    public Exercise toEntity(ExerciseCreateDto exerciseCreateDto, Long workoutExerciseId) {
        if (exerciseCreateDto == null) return null;

        Exercise exercise = new Exercise();
        exercise.setReps(exerciseCreateDto.reps());
        exercise.setWeight(exerciseCreateDto.weight());

        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(workoutExerciseId)
                .orElseThrow(() -> new RuntimeException("not fuond"));
        exercise.setWorkoutExercise(workoutExercise);

        return exercise;
    }
}
