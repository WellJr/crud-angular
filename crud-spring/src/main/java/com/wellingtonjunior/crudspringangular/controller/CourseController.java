package com.wellingtonjunior.crudspringangular.controller;

import com.wellingtonjunior.crudspringangular.domain.Course;
import com.wellingtonjunior.crudspringangular.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/courses")
public class CourseController {


    private final CourseRepository courseRepository;

    // Dependency Injection
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public @ResponseBody List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @PostMapping
//    @ResponseStatus(code = HttpStatus.CREATED) <-- forma alternativa ao response entity
    public ResponseEntity<Course> save(@RequestBody Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }
}
