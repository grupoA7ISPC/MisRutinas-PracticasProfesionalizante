import { Component } from '@angular/core';
import { Clase } from 'src/app/models/clase';
import { ClasesService } from 'src/app/services/clases.service';

@Component({
  selector: 'app-products',
  template: `<div class="container">
  <div *ngFor="let clase of clases">
    <app-card-prod  [clase]="clase"></app-card-prod>
  </div>

  </div>`,
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {

  constructor (private clasesService: ClasesService) { }
  clases: Clase[] = []

  ngOnInit() {
    this.clasesService.getAllClasses().subscribe(data => {
    this.clases = data
    console.log(this.clases); //Cambiar para renderizado luego.
    });
  }

}
