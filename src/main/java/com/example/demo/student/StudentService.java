package com.example.demo.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Jan",
                        21,
                        LocalDate.of(2001, Month.DECEMBER, 19),
                        "jan.kowalski19045@gmail.com"

                )
        );
    }
}
