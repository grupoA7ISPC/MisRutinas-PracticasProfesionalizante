import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { ProductsComponent } from './pages/products/products.component';
import { RegistroComponent } from './pages/registro/registro.component';
import { CardProdComponent } from './components/card-prod/card-prod.component';
import { QuienesSomosComponent } from './pages/quienes-somos/quienes-somos.component';
import { CardIntegranteComponent } from './components/card-integrante/card-integrante.component';
import { ContactoComponent } from './pages/contacto/contacto.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProductsComponent,
    RegistroComponent,
    CardProdComponent,
    QuienesSomosComponent,
    CardIntegranteComponent,
    ContactoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
