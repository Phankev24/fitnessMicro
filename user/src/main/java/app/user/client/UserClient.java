package app.user.client;

import app.user.dtos.WorkoutResponseDto;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class UserClient {
    private final RestTemplate restTemplate;
    private final String workoutServiceUrl = "http://localhost:8081/api/workout";

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
}