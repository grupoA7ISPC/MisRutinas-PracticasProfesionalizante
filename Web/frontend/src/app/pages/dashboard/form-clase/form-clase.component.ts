import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Clase } from 'src/app/models/clase';
import { ClasesService } from 'src/app/services/clases.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-clase',
  templateUrl: './form-clase.component.html',
  styleUrls: ['./form-clase.component.css']
})
export class FormClaseComponent {
  public form;
  private selectedFile: File | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private clasesService: ClasesService,
    private router: Router
    ) {
    this.form = this.formBuilder.group({
      name: ['', [Validators.required, Validators.maxLength(45), Validators.minLength(1)]],
      price:  ['', [Validators.required, Validators.max(999999), Validators.min(0)]],
      description:  ['', [Validators.required, Validators.maxLength(400), Validators.minLength(1)]],
      image:  ['', [Validators.required]],
      duration:  ['', [Validators.required, Validators.maxLength(100), Validators.minLength(1)]]
    });
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }

  onSubmit(e: Event) {
    e.preventDefault();
    if (this.form.valid) {
      this.crearClase();
    } else {
      console.error('Formulario inválido');
    }
  }

  crearClase() {
    const formData = this.form.value;
    const formulario = new FormData();

    formulario.append('nombre', formData.name ?? '');
    formulario.append('precio', formData.price?.toString() ?? '0');
    formulario.append('descripcion', formData.description ?? '');
    if (this.selectedFile) {
      formulario.append('imagen', this.selectedFile, this.selectedFile.name);
    }
    formulario.append('duracion', formData.duration ?? '');

    this.clasesService.createClass(formulario).subscribe(
      (response) => {
        console.log('Clase creada exitosamente:', response);
        this.router.navigate(['/dashboard']);
      },
      (error) => {
        console.error('Error al crear la clase:', error);
      }
    );
  }


  get name(){
    return this.form.get('name');
  }

  get price(){
    return this.form.get('price');
  }

  get description(){
    return this.form.get('description');
  }

  get image(){
    return this.form.get('image');
  }

  get duration(){
    return this.form.get('duration');
  }
}
