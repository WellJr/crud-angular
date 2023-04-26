import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Course } from '../model/course';
import { CoursesService } from './../services/courses.service';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses$: Observable<Course[]>; //) $ é uma prática para indicar que o dado é do tipo observable.



 // coursesService: CoursesService;

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
    ) {

    // this.courses = [];

    // this.coursesService = new CoursesService();


    this.courses$ = this.coursesService.list().pipe(
      catchError(error => {
        this.onError('Erro ao carregar cursos.');
        return of([])
      })
    );

    //outra forma:
//    this.coursesService.list().subscribe(courses => {
//      this.courses = courses;
//    });
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg,
    });

  }

  ngOnInit(): void {

  }

  onAdd() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

}
