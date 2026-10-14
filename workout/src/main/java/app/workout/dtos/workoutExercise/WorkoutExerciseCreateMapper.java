package app.workout.dtos.workoutExercise;

import app.workout.entities.WorkoutExercise;
import app.workout.service.WorkoutExerciseService;
import org.springframework.stereotype.Component;

@Component
public class WorkoutExerciseCreateMapper {
    public WorkoutExercise toEntity(WorkoutExerciseCreateDto workoutExerciseCreateDto){
        if(workoutExerciseCreateDto == null) return null;

        WorkoutExercise we = new WorkoutExercise();
        we.setExerciseName(workoutExerciseCreateDto.exerciseName());

        return we;
    }
}
