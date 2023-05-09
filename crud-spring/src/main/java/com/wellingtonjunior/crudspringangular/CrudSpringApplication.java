package com.wellingtonjunior.crudspringangular;

import com.wellingtonjunior.crudspringangular.domain.Course;
import com.wellingtonjunior.crudspringangular.enums.Category;
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
			c.setStatus("Ativo");

			courseRepository.save(c);
		};
	}

}
