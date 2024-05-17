import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from "@angular/common/http";
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UsuarioDTO } from 'src/app/models/usuario';

@Injectable({
  providedIn: 'root'
})

export class UsuarioService {
  url: string = "http://127.0.0.1:8000/api/v1/registro/"; // Añadir / si se necesita*

  constructor(private http:HttpClient){
    console.log("Servicio de Usuarios está corriendo...");
  }

  onCrearUsuario(usuario: UsuarioDTO): Observable<UsuarioDTO>{
    return this.http.post<UsuarioDTO>(this.url, usuario).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error(error);
        return throwError(null);
      })
    );
  }
}
