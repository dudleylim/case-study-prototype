import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ButtonComponent } from './components/button/button.component';
import { MainContainerComponent } from './components/main-container/main-container.component';
import { SearchComponent } from './components/search/search.component';
import { InputTextComponent } from './components/input-text/input-text.component';
import { FilterComponent } from './components/filter/filter.component';



@NgModule({
  declarations: [
    ButtonComponent,
    MainContainerComponent,
    SearchComponent,
    InputTextComponent,
    FilterComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule
  ],
  exports: [
    ButtonComponent,
    MainContainerComponent,
    SearchComponent
  ]
})
export class SharedModule { }
