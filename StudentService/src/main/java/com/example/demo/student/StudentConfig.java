package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student gaurav = new Student(
                    1L,
                    "Gaurav",
                    "Gaurav.kharel@gmail.com",
                    LocalDate.of(2002, Month.JANUARY, 16)
            );

            Student brinda = new Student(
                    "Brinda",
                    "Brinda.kharel@gmail.com",
                    LocalDate.of(1999, Month.JUNE, 13)
            );

            repository.saveAll(
                    List.of(gaurav, brinda)
            );
        };
    }
}
