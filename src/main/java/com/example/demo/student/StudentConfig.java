package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.DECEMBER;


@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student jan = new Student(

                    "Jan",
                    LocalDate.of(2001, DECEMBER, 19),
                    "jan.kowalski19045@gmail.com"
            );

            Student alex = new Student(

                    "Alex",
                    LocalDate.of(2004, DECEMBER, 26),
                    "alex@gmail.com"
            );

            repository.saveAll(
                    List.of(jan, alex)
            );
        };
    }
}
