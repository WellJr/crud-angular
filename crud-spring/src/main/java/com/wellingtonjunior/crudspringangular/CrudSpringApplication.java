package com.wellingtonjunior.crudspringangular;

import com.wellingtonjunior.crudspringangular.domain.Course;
import com.wellingtonjunior.crudspringangular.domain.Lesson;
import com.wellingtonjunior.crudspringangular.enums.Category;
import com.wellingtonjunior.crudspringangular.enums.Status;
import com.wellingtonjunior.crudspringangular.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();
			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONT_END);
			c.setStatus(Status.ACTIVE);

			Lesson lesson = new Lesson();
			lesson.setName("Introdução");
			lesson.setYoutubeUrl("https://youtu.be/Nb4uxLxdvxo");
			lesson.setCourse(c);

			c.getLessons().add(lesson);

			courseRepository.save(c);
		};
	}

}
