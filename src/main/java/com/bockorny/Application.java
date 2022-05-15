package com.bockorny;

import com.bockorny.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.bockorny"})
public class Application {

    @Autowired
    private MovieService movieService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            try (final InputStream is = getClass().getClassLoader().getResourceAsStream("movies.csv")) {
                movieService.importMoviesByInputStream(is);
            }
        };
    }
}
