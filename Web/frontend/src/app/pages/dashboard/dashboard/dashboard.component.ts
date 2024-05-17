import { Component, OnInit } from '@angular/core';
import { Clase } from 'src/app/models/clase';
import { ClasesService } from 'src/app/services/clases.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Usuario } from 'src/app/models/usuario';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  clases: Clase[] = [];
  usuario: Usuario | null = null;
  username: string = "";

  constructor(private clasesService: ClasesService, private authService: AuthService) {}

  ngOnInit() {
    this.usuario = this.authService.usuarioAutenticado;
    if (this.usuario) {
      this.username = `${this.usuario.nombre} ${this.usuario.apellido}`;
    }
    this.clasesService.getAllClasses().subscribe(data => {
      this.clases = data;
    });
  }
}
