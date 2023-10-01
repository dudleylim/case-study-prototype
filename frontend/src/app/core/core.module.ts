import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AppRoutingModule } from '../app-routing.module';
import { SharedModule } from '@shared/shared.module';
import { NavDropdownComponent } from './components/nav-dropdown/nav-dropdown.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './pages/home/home.component';
import { HomeButtonComponent } from './components/home-button/home-button.component';



@NgModule({
  declarations: [
    NavbarComponent,
    NavDropdownComponent,
    PageNotFoundComponent,
    FooterComponent,
    HomeComponent,
    HomeButtonComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
    SharedModule
  ],
  exports: [
    NavbarComponent,
    FooterComponent
  ]
})
export class CoreModule { }
