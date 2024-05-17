import { Component, OnInit } from '@angular/core';
import { Clase } from 'src/app/models/clase';
import { ClasesService } from 'src/app/services/clases.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  clases: Clase[] = [];

  constructor(private clasesService: ClasesService) {}

  ngOnInit() {
    this.clasesService.getAllClasses().subscribe(data => {
      this.clases = data;
    });
  }
}