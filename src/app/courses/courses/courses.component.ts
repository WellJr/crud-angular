import { CoursesService } from './../services/courses.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses: Course[] = [];

  //define as colunas que serão exibidas
  displayedColumns = ['name', 'category'];


 // coursesService: CoursesService;

  constructor(private coursesService: CoursesService) {
    // this.courses = [];

    // this.coursesService = new CoursesService();

  }

  ngOnInit(): void {

    //neste caso não há diferenã entre declarar no ngOnInit ou no Constructor
    this.courses = this.coursesService.list();
  }

}
