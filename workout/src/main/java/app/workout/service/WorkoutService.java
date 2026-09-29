package app.workout.service;

import app.workout.dtos.WorkoutCreateDto;
import app.workout.dtos.WorkoutCreateMapper;
import app.workout.dtos.WorkoutResponseDto;
import app.workout.dtos.WorkoutResponseMapper;
import app.workout.workout.Workout;
import app.workout.workout.WorkoutRepository;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutResponseMapper workoutResponseMapper;
    private final WorkoutCreateMapper workoutCreateMapper;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutResponseMapper workoutResponseMapper, WorkoutCreateMapper workoutCreateMapper){
        this.workoutRepository = workoutRepository;
        this.workoutResponseMapper = workoutResponseMapper;
        this.workoutCreateMapper = workoutCreateMapper;
    }

    public List<WorkoutResponseDto> getAllWorkouts(){
        return workoutRepository.findAll()
                .stream()
                .map(workoutResponseMapper::toDTO)
                .toList();
    }

    public WorkoutResponseDto createWorkout(WorkoutCreateDto workoutCreateDto){
        Workout workoutEntity = workoutCreateMapper.toEntity(workoutCreateDto);
        Workout savedWorkout = workoutRepository.save(workoutEntity);
        return workoutResponseMapper.toDTO(savedWorkout);
    }
}
