import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CommClassRoutingModule } from './comm-class-routing.module';
import { ViewAllComponent } from './pages/view-all/view-all.component';
import { ViewSpecificComponent } from './pages/view-specific/view-specific.component';
import { AddComponent } from './pages/add/add.component';
import { UpdateComponent } from './pages/update/update.component';
import { SharedModule } from '@shared/shared.module';
import { CommClassCardComponent } from './components/comm-class-card/comm-class-card.component';


@NgModule({
  declarations: [
    ViewAllComponent,
    ViewSpecificComponent,
    AddComponent,
    UpdateComponent,
    CommClassCardComponent,
  ],
  imports: [
    CommonModule,
    CommClassRoutingModule,
    SharedModule
  ]
})
export class CommClassModule { }
