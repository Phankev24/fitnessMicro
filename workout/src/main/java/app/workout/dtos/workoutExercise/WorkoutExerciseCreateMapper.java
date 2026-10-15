package app.workout.dtos.workoutExercise;

import app.workout.entities.Workout;
import app.workout.entities.WorkoutExercise;
import app.workout.repository.WorkoutRepository;
import app.workout.service.WorkoutExerciseService;
import org.springframework.stereotype.Component;

@Component
public class WorkoutExerciseCreateMapper {

    private final WorkoutRepository workoutRepository;

    public WorkoutExerciseCreateMapper(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public WorkoutExercise toEntity(WorkoutExerciseCreateDto workoutExerciseCreateDto){
        if(workoutExerciseCreateDto == null) return null;

        WorkoutExercise we = new WorkoutExercise();
        we.setExerciseName(workoutExerciseCreateDto.exerciseName());

        if(workoutExerciseCreateDto.internalWorkoutId() != null){
            Workout workout = workoutRepository.findById(workoutExerciseCreateDto.internalWorkoutId())
                    .orElseThrow(() -> new RuntimeException("Workout not found with this id: " + workoutExerciseCreateDto.internalWorkoutId()));
            we.setWorkout(workout);
        }

        return we;
    }
}
