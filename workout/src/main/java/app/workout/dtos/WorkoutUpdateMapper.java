package app.workout.dtos;

import app.workout.entities.Workout;
import org.springframework.stereotype.Component;

@Component
public class WorkoutUpdateMapper {
    public void updateEntityFromDto(WorkoutUpdateDto dto, Workout entity){
        if(dto == null || entity == null){
            return;
        }

        if(dto.workoutName() != null){
            entity.setWorkoutName(dto.workoutName());
        }
        if(dto.workoutDescription() != null){
            entity.setWorkoutDescription(dto.workoutDescription());
        }
        if(dto.workoutType() != null){
            entity.setWorkoutType(dto.workoutType());
        }
        if(dto.workoutDateTime() != null){
            entity.setWorkoutDateTime(dto.workoutDateTime());
        }
    }
}
