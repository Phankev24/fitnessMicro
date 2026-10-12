package app.user.client;

import app.user.dtos.workout.WorkoutResponseDto;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutClient {
    private final RestTemplate restTemplate;
    private final String workoutServiceUrl = "http://localhost:8081/api/workout";

    public WorkoutClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<WorkoutResponseDto> getWorkoutsByUserId(UUID userId){
        String url = workoutServiceUrl +"/user/" + userId;

        ResponseEntity<List<WorkoutResponseDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<WorkoutResponseDto>>() {}

        );
        return response.getBody();
    }
}
