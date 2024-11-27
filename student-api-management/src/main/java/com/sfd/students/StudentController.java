package com.sfd.students;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kuldeep
 */

@RestController
@RequestMapping(value = "students")
public record StudentController(StudentService studentService) {
    @PostMapping("create")
    public ResponseEntity<Student> createUser(@RequestBody CreateStudentRequest createStudentRequest) {
        return ResponseEntity.ok(studentService.createUser(createStudentRequest));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllUsers() {
        return ResponseEntity.ok(studentService.findAll());
    }
}
