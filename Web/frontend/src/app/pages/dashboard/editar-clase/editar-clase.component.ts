// editar-clase.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Clase } from 'src/app/models/clase';
import { ClasesService } from 'src/app/services/clases.service';

@Component({
  selector: 'app-editar-clase',
  templateUrl: './editar-clase.component.html',
  styleUrls: ['./editar-clase.component.css']
})
export class EditarClaseComponent implements OnInit {
  clase: Clase | undefined;
  imagenURL: string | undefined;
  formulario: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private clasesService: ClasesService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.formulario = this.formBuilder.group({
      nombre: ['', Validators.required],
      precio: ['', Validators.required],
      descripcion: ['', Validators.required],
      duracion: ['', Validators.required],
      imagen: ['']
    });
  }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.clasesService.getClass(id).subscribe(clase => {
      this.clase = clase;
      this.imagenURL = clase.imagen;
      this.formulario.patchValue({
        nombre: clase.nombre,
        precio: clase.precio,
        descripcion: clase.descripcion,
        duracion: clase.duracion
      });
    });
  }

  onSubmit(): void {
    if (this.formulario.valid && this.clase) {
      this.actualizarClase();
    } else {
    }
  }

  actualizarClase(): void {
    if (this.formulario.valid && this.clase) {
      // Obtener e valor del campo imagen del formulario
      const imagenInput = this.formulario.get('imagen');
      const imagenFile = imagenInput ? imagenInput.value : null;
      
      // Crear un nuevo objeto claseActualizada con los valores del formulario
      const claseActualizada = { ...this.clase, ...this.formulario.value };
      
      // Actualizar la clase cn la nueva imagen
      this.clasesService.updateClass(claseActualizada, this.clase.id_clase, imagenFile).subscribe(
        () => {
          // redirigir al dashboard
          this.router.navigate(['/dashboard']);
        },
        error => {
          // Para mostrrar el error
          console.error('Error al actualizar la clase:', error);
        }
      );
    } else {
    }
  }

  // manejar la selección de la imagen
  onImagenSeleccionada(event: any): void {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imagenURL = e.target.result;
        this.formulario.patchValue({ imagen: file });
      };
      reader.readAsDataURL(file);
    }
  }
}