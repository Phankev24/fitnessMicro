package app.workout.service;

import app.workout.dtos.exercise.ExerciseCreateDto;
import app.workout.dtos.exercise.ExerciseCreateMapper;
import app.workout.dtos.exercise.ExerciseResponseDto;
import app.workout.dtos.exercise.ExerciseResponseMapper;
import app.workout.entities.Exercise;
import app.workout.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseCreateMapper exerciseCreateMapper;
    private final ExerciseResponseMapper exerciseResponseMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseCreateMapper exerciseCreateMapper, ExerciseResponseMapper exerciseResponseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseCreateMapper = exerciseCreateMapper;
        this.exerciseResponseMapper = exerciseResponseMapper;
    }

    public ExerciseResponseDto createExercise(Long workoutExerciseId, ExerciseCreateDto exerciseCreateDto) {
        Exercise exerciseEntity = exerciseCreateMapper.toEntity(exerciseCreateDto, workoutExerciseId);
        Exercise savedExercise = exerciseRepository.save(exerciseEntity);
        return exerciseResponseMapper.toDTO(savedExercise);
    }
}
