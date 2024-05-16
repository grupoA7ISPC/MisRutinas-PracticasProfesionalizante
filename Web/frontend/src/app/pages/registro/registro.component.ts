import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})

export class RegistroComponent {
  public registerForm;

  constructor(private formBuilder: FormBuilder,  private router: Router) {
    this.registerForm = this.formBuilder.group({
      nombre: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
      apellido : ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
      email : ['',[Validators.required, Validators.email]],
      password1: ['', [Validators.required,Validators.minLength(8), Validators.maxLength(16)]],
      password2: ['', Validators.required],
      fecha: ['', [Validators.required, this.fechaValidator]],
      dni: ['', [Validators.required, Validators.pattern('^[0-9]{8}$')]],
      telefono: ['', [Validators.required, Validators.pattern('^[0-9]{10,15}$')]],
      checkbox : ['',[Validators.required, Validators.requiredTrue]]
    }, { validator: this.passwordMatchValidator('password1', 'password2')});
  }

  passwordMatchValidator(password1Key: string, password2Key: string): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const password1 = control.get(password1Key)?.value;
      const password2 = control.get(password2Key)?.value;

      if (password1 === password2) {
        return null;
      }
      return { passwordMismatch: true };
    };
  }

  fechaValidator(control: FormControl) {
    const fechaNacimiento = new Date(control.value);
    const fechaMinima = new Date();
    fechaMinima.setFullYear(fechaMinima.getFullYear() - 13); // Restar 13 años a la fecha actual

    if (isNaN(fechaNacimiento.getTime())) {
      return { fechaInvalida: true }; // Retorna una clave personalizada para el error
    }

    if (fechaNacimiento > fechaMinima) {
      return { fechaMinima: true }; // Retorna una clave personalizada para el error
    }

    if (fechaNacimiento.getFullYear() < fechaMinima.getFullYear() - 99) {
      return { fechaInvalida: true }; // Retorna una clave personalizada para el error
    }

    return null; // Retorna null si la validación es exitosa
  }

  sendForm(event: Event): void {
    event.preventDefault();
    if (this.registerForm.valid) {
      console.log("Enviando al servidor...");
      // Lógica para enviar los datos al servidor
    } else {
      this.registerForm.markAllAsTouched();
    }
  }

  get Nombre() {
    return this.registerForm.get('nombre');
  }


  get Apellido() {
    return this.registerForm.get('apellido');
  }

  get Email() {
    return this.registerForm.get('email');
  }

  get Password1() {
    return this.registerForm.get('password1');
  }

  get Password2() {
    return this.registerForm.get('password2');
  }

  get Fecha() {
    return this.registerForm.get('fecha');
  }

  get Dni() {
    return this.registerForm.get('dni');
  }

  get Telefono() {
    return this.registerForm.get('telefono');
  }

  get Checkbox() {
    return this.registerForm.get('checkbox');
  }
}
