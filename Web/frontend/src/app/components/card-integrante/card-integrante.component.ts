import { Component } from '@angular/core';
import {Integrante} from 'src/app/models/integrante'

@Component({
  selector: 'app-card-integrante',
  templateUrl: './card-integrante.component.html',
  styleUrls: ['./card-integrante.component.css']
})
export class CardIntegranteComponent {
  constructor() { }

  listaIntegrantes: Integrante[] = [
    {
      id: 2,
      nombre:'Carina Payleman',
      rol: 'Desarrolladora',
      foto: 'carina.jpg',
      github: 'PaylemanC',
      linkedin: 'https://www.linkedin.com/in/carina-rocio-payleman/',
      info: ''
    },
    {
      id: 3,
      nombre:'Esteban Martins',
      rol: 'Desarrollador',
      foto: 'esteban.jpg',
      github: 'estebanmartins',
      linkedin: '',
      info: ''
    },
    {
      id: 4,
      nombre:'Horacio Quiroga',
      rol: 'Desarrollador',
      foto: 'horacio.jpg',
      github: 'horacioequiroga',
      linkedin: '',
      info: ''
    },
    {
      id: 5,
      nombre:'Federico Ocaranza',
      rol: 'Desarrollador',
      foto: 'fede.jpg',
      github: 'fede9087',
      linkedin: '',
      info: ''
    },
    {
      id: 7,
      nombre:'Veronica Torres',
      rol: 'Desarrolladora',
      foto: 'vero.jpg',
      github: 'verofx',
      linkedin: '',
      info: ''
    },
  ];

}

