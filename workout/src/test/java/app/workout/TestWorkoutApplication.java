package app.workout;

import org.springframework.boot.SpringApplication;

public class TestWorkoutApplication {

    public static void main(String[] args) {
        SpringApplication.from(WorkoutApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
