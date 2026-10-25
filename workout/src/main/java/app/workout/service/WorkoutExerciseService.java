package app.workout.service;

import app.workout.dtos.workoutExercise.*;
import app.workout.entities.WorkoutExercise;
import app.workout.repository.WorkoutExerciseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutExerciseService {
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutExerciseResponseMapper workoutExerciseResponseMapper;
    private final WorkoutExerciseCreateMapper workoutExerciseCreateMapper;
    private final WorkoutExerciseUpdateMapper workoutExerciseUpdateMapper;


    public WorkoutExerciseService(WorkoutExerciseRepository workoutExerciseRepository, WorkoutExerciseResponseMapper workoutExerciseResponseMapper, WorkoutExerciseCreateMapper workoutExerciseCreateMapper, WorkoutExerciseUpdateMapper workoutExerciseUpdateMapper) {
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.workoutExerciseResponseMapper = workoutExerciseResponseMapper;
        this.workoutExerciseCreateMapper = workoutExerciseCreateMapper;
        this.workoutExerciseUpdateMapper = workoutExerciseUpdateMapper;
    }

    public List<WorkoutExerciseResponseDto> getAllWorkoutSessions(){
        return workoutExerciseRepository.findAll()
                .stream()
                .map(workoutExerciseResponseMapper::toDTO)
                .toList();
    }

    public WorkoutExerciseResponseDto createWorkoutSession(UUID externalWorkoutId, WorkoutExerciseCreateDto workoutExerciseCreateDto){
        WorkoutExercise workoutExerciseEntity = workoutExerciseCreateMapper.toEntity(externalWorkoutId ,workoutExerciseCreateDto);
        WorkoutExercise savedWorkoutExercise = workoutExerciseRepository.save(workoutExerciseEntity);
        return workoutExerciseResponseMapper.toDTO(savedWorkoutExercise);
    }

    public WorkoutExerciseResponseDto updateWorkoutExercise(UUID externalWorkoutExerciseId, WorkoutExerciseResponseDto workoutExerciseUpdateDto){
        WorkoutExercise workoutExercise = workoutExerciseRepository.findByExternalWorkoutExerciseId(externalWorkoutExerciseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "WorkoutExercise not found with id: " + externalWorkoutExerciseId));
        workoutExerciseUpdateMapper.updateEntityFromDto(workoutExerciseUpdateDto, workoutExercise);
        WorkoutExercise updatedWorkoutExercise = workoutExerciseRepository.save(workoutExercise);
        return workoutExerciseResponseMapper.toDTO(updatedWorkoutExercise);
    }

    public void deleteWorkoutExercise(UUID externalWorkoutExerciseId){
        WorkoutExercise workoutExercise = workoutExerciseRepository.findByExternalWorkoutExerciseId(externalWorkoutExerciseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        workoutExerciseRepository.delete(workoutExercise);
    }
}
