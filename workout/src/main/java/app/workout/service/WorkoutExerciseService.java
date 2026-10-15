package app.workout.service;

import app.workout.dtos.workoutExercise.WorkoutExerciseCreateDto;
import app.workout.dtos.workoutExercise.WorkoutExerciseCreateMapper;
import app.workout.dtos.workoutExercise.WorkoutExerciseResponseDto;
import app.workout.dtos.workoutExercise.WorkoutExerciseResponseMapper;
import app.workout.entities.WorkoutExercise;
import app.workout.repository.WorkoutExerciseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutExerciseService {
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutExerciseResponseMapper workoutExerciseResponseMapper;
    private final WorkoutExerciseCreateMapper workoutExerciseCreateMapper;

    public WorkoutExerciseService(WorkoutExerciseRepository workoutExerciseRepository, WorkoutExerciseResponseMapper workoutExerciseResponseMapper, WorkoutExerciseCreateMapper workoutExerciseCreateMapper) {
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.workoutExerciseResponseMapper = workoutExerciseResponseMapper;
        this.workoutExerciseCreateMapper = workoutExerciseCreateMapper;
    }

    public List<WorkoutExerciseResponseDto> getAllWorkoutSessions(){
        return workoutExerciseRepository.findAll()
                .stream()
                .map(workoutExerciseResponseMapper::toDTO)
                .toList();
    }

    public WorkoutExerciseResponseDto createWorkoutSession(WorkoutExerciseCreateDto workoutExerciseCreateDto){
        WorkoutExercise workoutExerciseEntity = workoutExerciseCreateMapper.toEntity(workoutExerciseCreateDto);
        WorkoutExercise savedWorkoutExercise = workoutExerciseRepository.save(workoutExerciseEntity);
        return workoutExerciseResponseMapper.toDTO(savedWorkoutExercise);
    }
}
