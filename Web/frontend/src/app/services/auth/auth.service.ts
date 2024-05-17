import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { UsuarioLoginDTO } from './usuario.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url ="http://127.0.0.1:8000/api/v1/login/";

  currentUserSubject: BehaviorSubject<any>;
  currentUser: Observable<any>;
  public loggedIn: BehaviorSubject<boolean>;

  constructor(private http:HttpClient) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser') || '{}'));
    this.currentUser = this.currentUserSubject.asObservable();
    this.loggedIn = new BehaviorSubject<boolean>(false);
  }

  login(usuario: UsuarioLoginDTO): Observable<any> {
    return this.http.post<any>(this.url, usuario).pipe(
      map(data => {
        console.log(data)
        localStorage.setItem('currentUser', JSON.stringify(data));
        this.currentUserSubject.next(data);
        this.loggedIn.next(true);
        return data;
      })
    );
  }

  logout(): void{
    localStorage.removeItem('currentUser');
    // this.currentUserSubject.next(null);
    this.loggedIn.next(false);
  }

  get usuarioAutenticado(): any {
    // return this.currentUserSubject.value;
    return JSON.parse(localStorage.getItem('currentUser') || '{}');
  }

  get estarAutenticado(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }
}
