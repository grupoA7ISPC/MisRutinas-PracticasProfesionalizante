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
    this.loadClasses();
  }

  loadClasses(): void {
    this.clasesService.getAllClasses().subscribe(
      (data: Clase[]) => {
        this.clases = data;
      },
      error => {
        console.error('Error al cargar las clases:', error);
      }
    );
  }

  eliminarClase(id: number): void {
    this.clasesService.deleteClass(id).subscribe(
      () => {
        // elimina y se actualizar la lista de clases
        this.loadClasses();
      },
      error => {
        console.error('Error al eliminar la clase:', error);
      }
    );
  }
}
