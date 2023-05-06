package com.wellingtonjunior.crudspringangular.service;

import com.wellingtonjunior.crudspringangular.domain.Course;
import com.wellingtonjunior.crudspringangular.exeption.RecordNotFoundException;
import com.wellingtonjunior.crudspringangular.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.List;
import java.util.Optional;

@Validated
@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    // Dependency Injection
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(@NotNull @Positive Long id){

        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));

    }

    public Course save(@Valid Course course){
        return courseRepository.save(course);
    }

    public Course update(@NotNull @Positive Long id, @Valid Course course) {

        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {

        courseRepository.delete(
                courseRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }


}
