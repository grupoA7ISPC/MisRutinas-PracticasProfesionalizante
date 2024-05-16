import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Clase } from '../models/clase';

@Injectable({
  providedIn: 'root'
})
export class ClasesService {

  constructor(private http: HttpClient) { }

  private URL_API: string = './assets/data/clases.json'; // TEMPORAL, SOLO GETs

  getAllClasses(): Observable <any> {
    return this.http.get<Clase[]>(this.URL_API);
  }

  getClass(id: number): Observable <any> {
    return this.http.get<Clase[]>(`${this.URL_API}/${id}`);
  }

  createClass(clase: Clase): Observable <any> {
    return this.http.post<Clase>(this.URL_API, clase);
  }

  updateClass(clase: Clase): Observable <any> {
    return this.http.put<Clase>(`${this.URL_API}/${clase.id_clase}`, clase);
  }

  deleteClass(id: number): Observable <any> {
    return this.http.delete<Clase>(`${this.URL_API}/${id}`);
  }
}
