import { Component } from '@angular/core';
import { Clase } from 'src/app/models/clase';
import { ClasesService } from 'src/app/services/clases.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  constructor (private clasesService: ClasesService) { }
  clases: Clase[] = []

  ngOnInit() {
    this.clasesService.getAllClasses().subscribe(data => {
    this.clases = data
    console.log(this.clases); //Cambiar para renderizado luego.
  });
}
}


