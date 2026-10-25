package app.workout.dtos.workoutExercise;

import app.workout.entities.WorkoutExercise;
import org.springframework.stereotype.Component;

@Component
public class WorkoutExerciseUpdateMapper {
    public void updateEntityFromDto(WorkoutExerciseUpdateDto updateDto, WorkoutExercise entity){
        if(updateDto == null || entity == null){
            return;
        }

        if(updateDto.exerciseName() != null){
            entity.setExerciseName(updateDto.exerciseName());
        }
    }
}
