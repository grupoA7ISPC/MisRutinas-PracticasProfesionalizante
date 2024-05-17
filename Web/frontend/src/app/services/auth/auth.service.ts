import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Usuario, UsuarioLoginDTO } from 'src/app/models/usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private URL_API: string = "http://127.0.0.1:8000/api/v1/login/";

  private currentUserSubject: BehaviorSubject<Usuario | null>;
  public currentUser: Observable<Usuario | null>;
  public loggedIn: BehaviorSubject<boolean>;

  constructor(private http:HttpClient) {
    const storedUser = localStorage.getItem('currentUser');
    this.currentUserSubject = new BehaviorSubject<Usuario | null>(storedUser ? new Usuario(JSON.parse(storedUser).user) : null);
    this.currentUser = this.currentUserSubject.asObservable();
    this.loggedIn = new BehaviorSubject<boolean>(!!storedUser);
  }

  login(usuario: UsuarioLoginDTO): Observable<any> {
    return this.http.post<any>(this.URL_API, usuario).pipe(
      map(data => {
        console.log("data desde authService.login: ", data)
        const user = new Usuario(data.user);
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUserSubject.next(user);
        this.loggedIn.next(true);
        return user;
      })
    );
  }

  logout(): void{
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.loggedIn.next(false);
  }

  get usuarioAutenticado(): any {
    return this.currentUserSubject.value;
  }

  get estarAutenticado(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }
}
