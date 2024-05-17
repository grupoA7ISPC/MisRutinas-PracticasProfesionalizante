import { Component, OnInit } from '@angular/core';
import { Clase } from 'src/app/models/clase';
import { ClasesService } from 'src/app/services/clases.service';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  clases: Clase[] = [];
  usuario: any;
  username: string = "";

  constructor(private clasesService: ClasesService, private authService: AuthService) {}

  ngOnInit() {
    this.usuario = this.authService.usuarioAutenticado;
    this.username = `${this.usuario.user.nombre} ${this.usuario.user.apellido}`;
    this.clasesService.getAllClasses().subscribe(data => {
      this.clases = data;
    });
  }
}
