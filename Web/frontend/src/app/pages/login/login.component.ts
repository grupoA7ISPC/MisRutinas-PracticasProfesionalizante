import { Component } from '@angular/core';
import { FormControl, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { UsuarioDTO, UsuarioLoginDTO } from 'src/app/models/usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email = new FormControl('',[]);
  password = new FormControl('',[]);
  usuario: UsuarioLoginDTO = new UsuarioDTO();

  public form;
  public error:any;

  constructor (private formBuilder: FormBuilder, private authService: AuthService, private router: Router){
    this.form = this.formBuilder.group({
      email:['',[Validators.required, Validators.email]],
      password :['',[Validators.required]]
    }
  )}

  get Email()
  {
    return this.form.get("email");
  }
  get Password()
  {
    return this.form.get("password");
  }

  enviarFormulario(event:Event): void {
    event.preventDefault();

    if (this.form.valid) {
      let user: UsuarioLoginDTO = {
        email: this.form.value.email,
        password: this.form.value.password
      };
      this.authService.login(user).subscribe({
        next: (data) => {
          // console.log("DATA: " + JSON.stringify(data));
          const currentUser = this.authService.usuarioAutenticado;
          if (currentUser) {
            this.router.navigate(['/dashboard']);
          }
        },
        error: (error) => {
          if (error.status === 400 && error.error && error.error.error === "Credenciales inválidas") {
            this.error = "Usuario y/o contraseña incorrecta.";
          } else {
            this.error = "Se ha producido un error inesperado. Por favor, inténtelo de nuevo más tarde.";
          }
        }
      });
    } else {
      console.log("Datos incompletos");
    }
  }
}
