import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent, ContactoComponent, DashboardComponent, LoginComponent, ProductsComponent, QuienesSomosComponent, RegistroComponent } from './pages';
import { FormClaseComponent } from './pages/dashboard/form-clase/form-clase.component';
import { AuthGuard } from './guards/auth.guard';
import { EditarClaseComponent } from './pages/dashboard/editar-clase/editar-clase.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'contacto', component: ContactoComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registro', component: RegistroComponent},
  {path: 'quienes-somos', component: QuienesSomosComponent},
  {path: 'planes', component: ProductsComponent},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path: 'crear-clase', component: FormClaseComponent},
  { path: 'editar-clase/:id', component: EditarClaseComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
