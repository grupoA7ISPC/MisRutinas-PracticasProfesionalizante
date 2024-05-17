import { Component, Input } from '@angular/core';
import { Clase } from 'src/app/models/clase';

@Component({
  selector: 'app-tab-clase',
  templateUrl: './tab-clase.component.html',
  styleUrls: ['./tab-clase.component.css']
})
export class TabClaseComponent {
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

