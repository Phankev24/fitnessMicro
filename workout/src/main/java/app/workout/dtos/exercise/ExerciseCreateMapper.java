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

    public Exercise toEntity(ExerciseCreateDto exerciseCreateDto) {
        if (exerciseCreateDto == null) return null;

        Exercise exercise = new Exercise();
        exercise.setReps(exerciseCreateDto.reps());
        exercise.setWeight(exerciseCreateDto.weight());

        // Set the WorkoutExercise relationship based on the workoutExerciseId
        if (exerciseCreateDto.workoutExerciseId() != null) {
            WorkoutExercise workoutExercise = workoutExerciseRepository.findById(exerciseCreateDto.workoutExerciseId())
                    .orElseThrow(() -> new RuntimeException("WorkoutExercise not found with id: " + exerciseCreateDto.workoutExerciseId()));
            exercise.setWorkoutExercise(workoutExercise);
        }

        return exercise;
    }
}
