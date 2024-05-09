import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Suscripcion } from '../models/suscripcion';

@Injectable({
  providedIn: 'root'
})
export class ClasesService {

  constructor(private http: HttpClient) { }

  private urlApi: string = '';

  getAllSubscriptions(): Observable <any> {
    return this.http.get<Suscripcion[]>(this.urlApi);
  }

  getSubscription(id: number): Observable <any> {
    return this.http.get<Suscripcion[]>(`${this.urlApi}/${id}`);
  }
}
