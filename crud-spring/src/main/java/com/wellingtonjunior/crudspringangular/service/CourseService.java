package com.wellingtonjunior.crudspringangular.service;

import com.wellingtonjunior.crudspringangular.dto.CourseDTO;
import com.wellingtonjunior.crudspringangular.dto.mapper.CourseMapper;
import com.wellingtonjunior.crudspringangular.exeption.RecordNotFoundException;
import com.wellingtonjunior.crudspringangular.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.ArrayList;
import java.util.List;

@Validated
@Service
public class CourseService {

    CourseRepository courseRepository;

    CourseMapper courseMapper;

    // Dependency Injection
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public CourseDTO findById(@NotNull @Positive Long id){

        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO save(@Valid @NotNull CourseDTO course){
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {

        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(courseMapper.convertCategoryValue(course.category()));
                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {

        courseRepository.delete(
                courseRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }


}
