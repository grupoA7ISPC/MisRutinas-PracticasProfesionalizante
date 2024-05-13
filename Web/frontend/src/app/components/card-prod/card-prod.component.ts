import { Component,Input } from '@angular/core';
import { Clase } from 'src/app/models/clase';

@Component({
  selector: 'app-card-prod',
  templateUrl: './card-prod.component.html',
  styleUrls: ['./card-prod.component.css']
})
export class CardProdComponent {
  @Input()
  clase:Clase = {
    id_clase:0,
    nombre:"",
    descripcion:"",
    imagen:"",
    duracion:"",
    precio:0
  }
}
