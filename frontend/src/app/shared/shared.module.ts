import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ButtonComponent } from './components/button/button.component';
import { MainContainerComponent } from './components/main-container/main-container.component';
import { SearchComponent } from './components/search/search.component';
import { InputTextComponent } from './components/input-text/input-text.component';
import { FilterComponent } from './components/filter/filter.component';
import { ButtonLinkComponent } from './components/button-link/button-link.component';
import { DropdownComponent } from './components/dropdown/dropdown.component';
import { TooltipComponent } from './components/tooltip/tooltip.component';



@NgModule({
  declarations: [
    ButtonComponent,
    MainContainerComponent,
    SearchComponent,
    InputTextComponent,
    FilterComponent,
    ButtonLinkComponent,
    DropdownComponent,
    TooltipComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule
  ],
  exports: [
    ButtonComponent,
    MainContainerComponent,
    SearchComponent,
    ButtonLinkComponent,
    InputTextComponent,
    DropdownComponent,
    TooltipComponent
  ]
})
export class SharedModule { }
