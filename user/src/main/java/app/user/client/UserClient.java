package app.user.client;

import app.user.dtos.workout.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class UserClient {
    private final RestTemplate restTemplate;
    private final String workoutServiceUrl = "http://localhost:8081/api/workout";
    private final String exerciseServiceUrl = "http://localhost:8081/api/exercise";
    private final String workoutExerciseServiceUrl = "http://localhost:8081/api/workoutExercise";

    public UserClient(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    public List<WorkoutResponseDto> getWorkouts(){
        ResponseEntity<List<WorkoutResponseDto>> response = restTemplate.exchange(
                workoutServiceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<WorkoutResponseDto>>() {}
        );
        return response.getBody();
    }

    public List<WorkoutResponseDto> getWorkoutsByUserId(UUID userId){
        String url = workoutServiceUrl + "/user/" + userId;
        ResponseEntity<List<WorkoutResponseDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<WorkoutResponseDto>>() {}
        );
        return response.getBody();
    }

    public WorkoutResponseDto userCreateWorkout(UUID userId, WorkoutCreateDto workoutCreateDto){
        HttpEntity<WorkoutCreateDto> request = new HttpEntity<>(workoutCreateDto);
        ResponseEntity<WorkoutResponseDto> response = restTemplate.postForEntity(
                workoutServiceUrl + "/user/" + userId,
                request,
                WorkoutResponseDto.class
        );
        return response.getBody();
    }



    //TODO: Let user create a workoutExercise from usercommscntroler
    public WorkoutExerciseResponseDto userCreateWorkoutExercise(WorkoutExerciseCreateDto workoutExerciseCreateDto){
        HttpEntity<WorkoutExerciseCreateDto> request = new HttpEntity<>(workoutExerciseCreateDto);
        ResponseEntity<WorkoutExerciseResponseDto> response = restTemplate.postForEntity(
                workoutExerciseServiceUrl,
                request,
                WorkoutExerciseResponseDto.class
        );
        return response.getBody();
    }


    @Operation(description = "Creates Exercise from user")
    public ExerciseResponseDto createExercise(ExerciseCreateDto exerciseCreateDto){
        HttpEntity<ExerciseCreateDto> request = new HttpEntity<>(exerciseCreateDto);
        ResponseEntity<ExerciseResponseDto> response = restTemplate.postForEntity(
                exerciseServiceUrl,
                request,
                ExerciseResponseDto.class
        );
        return response.getBody();
    }
}