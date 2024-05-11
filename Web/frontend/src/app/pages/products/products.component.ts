import { Component } from '@angular/core';
import { Clase } from 'src/app/models/clase';
import { ClasesService } from 'src/app/services/clases.service';

@Component({
  selector: 'app-products',
  template: `<div class="container">
  <!-- <div *ngFor="let suscripcion of suscripciones" class="box basic">
      <h2>{{suscripcion.nombre}}</h2>
      <p>{{ suscripcion.descripcion }}<p>
      <p>{{suscripcion.dur_dias}} dias</p>
      <p>Precio: {{ suscripcion.price |currency }}</p>
      <a routerLink="/pago" class="subscribe-button">Suscribirse</a>
  </div> -->
  <app-card-prod></app-card-prod>
  <app-card-prod></app-card-prod>
  <app-card-prod></app-card-prod>
  <app-card-prod></app-card-prod>
  <app-card-prod></app-card-prod>
  </div>`,
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {

  constructor (private clasesService: ClasesService) { }
  clases: Clase[] = []

//   ngOnInit() {
//     this.clasesService.getAllClasses()
//     .subscribe(data => {
//     console.log(data);
//      this.clases = data //Cambiar para renderizado luego.
//   });
//  }

}
