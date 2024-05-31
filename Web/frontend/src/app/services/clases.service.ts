import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Clase } from '../models/clase';

@Injectable({
  providedIn: 'root'
})
export class ClasesService {

  constructor(private http: HttpClient) { }

  private URL_API: string = 'http://127.0.0.1:8000/api/v1/clases/';

  getAllClasses(): Observable<any> {
    return this.http.get<Clase[]>(this.URL_API);
  }

  getClass(id: number): Observable<Clase> {
    return this.http.get<Clase>(`${this.URL_API}${id}/`);
  }

  createClass(clase: Clase): Observable<Clase> {
    return this.http.post<Clase>(this.URL_API, clase);
  }

  deleteClass(id: number): Observable<void> {
    return this.http.delete<void>(`${this.URL_API}${id}`);
  }

  updateClass(clase: Clase, id: number, imagenFile?: File | null): Observable<any> {
    const formData = new FormData();
    formData.append('nombre', clase.nombre);
    formData.append('precio', clase.precio.toString());
    formData.append('descripcion', clase.descripcion);
    formData.append('duracion', clase.duracion);
    //Solucionar el problema de la actualizacion no se realiza si no cargo de nuevo la img
    //
    //
    // Verificar si se ha subido una nueva imagen
    if (imagenFile !== null) {
      if (imagenFile) {
        // armar el nombre de la imagen con su extensión
        const nombreImagen = imagenFile.name;
        // Agregar la imagen al formulario con el nombre de la imagen
        formData.append('imagen', imagenFile, nombreImagen);
      }
    } else {
      // Si no se ha subido una nueva imagen, simplemente agregar la URL como la estabamos manejanso
      formData.append('imagen', clase.imagen);
    }

    return this.http.put<Clase>(`${this.URL_API}${id}/`, formData)
      .pipe(
        catchError(error => {
          console.error('Error al actualizar la clase:', error);
          return throwError('Hubo un problema al actualizar la clase.');
        })
      );
  }
}