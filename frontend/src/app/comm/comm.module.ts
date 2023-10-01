import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CommRoutingModule } from './comm-routing.module';
import { ViewAllComponent } from './pages/view-all/view-all.component';
import { ViewSpecificComponent } from './pages/view-specific/view-specific.component';
import { AddComponent } from './pages/add/add.component';
import { UpdateComponent } from './pages/update/update.component';


@NgModule({
  declarations: [
    ViewAllComponent,
    ViewSpecificComponent,
    AddComponent,
    UpdateComponent
  ],
  imports: [
    CommonModule,
    CommRoutingModule
  ]
})
export class CommModule { }
