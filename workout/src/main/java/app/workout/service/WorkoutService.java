package app.workout.service;

import app.workout.dtos.workout.*;
import app.workout.entities.Workout;
import app.workout.repository.WorkoutRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutResponseMapper workoutResponseMapper;
    private final WorkoutCreateMapper workoutCreateMapper;
    private final WorkoutUpdateMapper workoutUpdateMapper;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutResponseMapper workoutResponseMapper, WorkoutCreateMapper workoutCreateMapper, WorkoutUpdateMapper workoutUpdateMapper){
        this.workoutRepository = workoutRepository;
        this.workoutResponseMapper = workoutResponseMapper;
        this.workoutCreateMapper = workoutCreateMapper;
        this.workoutUpdateMapper = workoutUpdateMapper;
    }

    public List<WorkoutResponseDto> getAllWorkouts(){
        return workoutRepository.findAll()
                .stream()
                .map(workoutResponseMapper::toDTO)
                .toList();
    }

    public WorkoutResponseDto getWorkoutById(UUID externalWorkoutId){
        return workoutRepository.findByExternalWorkoutId(externalWorkoutId)
                .map(workoutResponseMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Workout not found with workoutId: " + externalWorkoutId));
    }

    public WorkoutResponseDto createWorkout(UUID externalUserId, WorkoutCreateDto workoutCreateDto){
        Workout workoutEntity = workoutCreateMapper.toEntity(workoutCreateDto);
        workoutEntity.setExternalUserId(externalUserId);
        Workout savedWorkout = workoutRepository.save(workoutEntity);
        return workoutResponseMapper.toDTO(savedWorkout);
    }

    public WorkoutResponseDto updateWorkout(UUID externalWorkoutId, WorkoutUpdateDto workoutUpdateDto) {
        Workout workout = workoutRepository.findByExternalWorkoutId(externalWorkoutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout not found with this id: " + externalWorkoutId));
        workoutUpdateMapper.updateEntityFromDto(workoutUpdateDto, workout);
        Workout updatedWorkout = workoutRepository.save(workout);
        return workoutResponseMapper.toDTO(updatedWorkout);
    }

    public void deleteWorkout(UUID externalWorkoutId){
        Workout workout = workoutRepository.findByExternalWorkoutId(externalWorkoutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout not found: " + externalWorkoutId));
        workoutRepository.delete(workout);
    }

    public List<WorkoutResponseDto> getWorkoutByUserId(UUID externalUserId){
        return workoutRepository.findByExternalUserId(externalUserId)
                .stream()
                .map(workoutResponseMapper::toDTO)
                .toList();
    }
}
