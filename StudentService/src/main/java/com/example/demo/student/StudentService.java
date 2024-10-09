package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    /*
    final: A non-access modifier used for classes, attributes,
    and methods, which makes them non-changeable (impossible to inherit or override.
    Use when you want a variable to always store the same value
     */
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken!");
        }
        studentRepository.save(student);

    }

    public void deleteStudent(Long studentId) {

        if (!studentRepository.existsById(studentId)){
            throw new IllegalStateException(
                    "Student with id " + studentId + "does not exist!");
        }

        studentRepository.deleteById(studentId);
    }

    /**
     * Transaction ensures that all the database operations are executed as a single unit of work.
     * If any operation in a transaction fails, the entire transaction is rolled back, ensuring
     * that the data remains consistent.
     *
     */
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exits!"));



        if (name != null &&
                !name.isEmpty() &&
                !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if (email != null &&
                !email.isEmpty() &&
                !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Email taken!");
            }

            student.setEmail(email);
        }
    }
}
