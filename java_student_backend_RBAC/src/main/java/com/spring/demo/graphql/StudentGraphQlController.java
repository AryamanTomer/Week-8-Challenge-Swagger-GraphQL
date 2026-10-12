package com.spring.demo.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.spring.demo.domain.Student;
import com.spring.demo.dto.StudentWriteDto;
import com.spring.demo.service.StudentService;

@Controller
public class StudentGraphQlController {

    private final StudentService studentService;

    public StudentGraphQlController(StudentService studentService) {
        this.studentService = studentService;
    }

    @QueryMapping
    public Student student(@Argument int id) {
        return studentService.getStudentById(id);
    }

    @MutationMapping
    public Student createStudent(
            @Argument String firstName,
            @Argument String lastName,
            @Argument String email) {

        StudentWriteDto student = new StudentWriteDto();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        return studentService.insertStudent(student);
    }
}