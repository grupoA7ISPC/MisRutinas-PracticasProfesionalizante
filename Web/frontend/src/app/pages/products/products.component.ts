import { Component } from '@angular/core';
import { Suscripcion } from 'src/app/models/suscripcion';
import { ClasesService } from 'src/app/services/clases.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {
  constructor (private clasesService:ClasesService) {

  }
  suscripciones: Suscripcion[] = [
    {
      id: 1,
      nombre: "Suscripcion Básica",
      descripcion: 
        "Acceso a funcionalidades básicas de nuestra plataforma"
      ,
      dur_dias: "Membresia Mensual",
      price: 0
    },
    {
      id: 2,
      nombre: "Premium Mensual",
      descripcion: 
        "Acceso completo a nuestra plataforma",
      dur_dias: "Membresia Mensual",
      price: 1000
    }
  ]
//   ngOnInit() {
//     this.clasesService.getAllSubscriptions() 
//     .subscribe(data => {
//     console.log(data);
//      this.suscripciones = data //Cambiar para renderizado luego. 
//   });
//  }

}
