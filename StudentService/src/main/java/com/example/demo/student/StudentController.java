package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    // Reference to studentService
    private final StudentService studentService;

    // This StudentService should be autowired into the constructor
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    /**
     * Gets the result
     */
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    /**
     * Adding new resources to our system
     * Take Request and map it to Student
     */
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }


    @DeleteMapping(path= "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    /**
     * Used when you want to update
     */
    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

}
