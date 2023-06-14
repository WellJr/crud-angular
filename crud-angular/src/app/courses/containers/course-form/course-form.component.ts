import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { CoursesService } from '../../services/courses.service';
import { Course } from '../../model/course';
import { Lesson } from '../../model/lesson';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form!: FormGroup;

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: CoursesService,
    private snackBar: MatSnackBar,
    private locarion: Location,
    private route: ActivatedRoute
    ) {
    // this.form
  }

  ngOnInit(): void {
    const course: Course = this.route.snapshot.data['course'];

    this.form = this.formBuilder.group({
      _id: [course._id],
      name: [course.name,
      [Validators.required,
        Validators.minLength(5),
        Validators.maxLength(100)]],
      category: [course.category, [Validators.required]],
      lessons: this.formBuilder.array(this.retiveLessons(course))
    });
    console.log(this.form);
    console.log(this.form.value);

  }

  private retiveLessons(course: Course){
    const lessons = [];
    if(course?.lessons) {
      course.lessons.forEach(lesson => lessons.push(lesson));
    }else {
      lessons.push(this.createLesson())
    }

    return lessons;
  }

  private createLesson(lesson: Lesson = {id: '', name: '', youtubeUrl: ''}){
      return this.formBuilder.group({
        id: [lesson.id],
        name: [lesson.name],
        youtubeUrl: [lesson.youtubeUrl]
      });
  }

  onSubmit(){
    this.service.save(this.form.value)
    .subscribe(
      result => this.onSuccess(),

      error => {
        this.onError();
      });
  }

  onCancel() {
    this.locarion.back();
  }

  private onSuccess() {
    this.snackBar.open('Curso criado com sucesso!', '', { duration: 5000 });
    this.locarion.back();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar curso!', '', { duration: 5000 });
  }

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);

    if(field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if(field?.hasError('minlength')) {
      const requiredLength = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Tamanho mínimo precisa ser de ${requiredLength} caracteres`;
    }

    if(field?.hasError('maxlength')) {
      const requiredLength = field.errors ? field.errors['maxlength']['requiredLength'] : 100;
      return `Tamanho máximo exedido de ${requiredLength} caracteres`;
    }

    return 'Campo inválido';

  }

}
