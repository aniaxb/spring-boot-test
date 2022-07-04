package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {

        return studentRepository.findAll();

//        return List.of(
//                new Student(
//                        1L,
//                        "Jan",
//                        21,
//                        LocalDate.of(2001, Month.DECEMBER, 19),
//                        "jan.kowalski19045@gmail.com"
//
//                )
//        );
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw  new IllegalStateException("email taken");
        }
//        System.out.println(student);
        studentRepository.save(student);
    }
}
