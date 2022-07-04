package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
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

    public void deleteStudent(Long studentId) {
        studentRepository.findById(studentId);
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id "+ studentId+ " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId+ " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);

        }
    }
}
