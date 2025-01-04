package org.example.singularpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SingularPointApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingularPointApplication.class, args);
    }

}
