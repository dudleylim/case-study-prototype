import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewAllComponent } from './pages/view-all/view-all.component';
import { ViewSpecificComponent } from './pages/view-specific/view-specific.component';
import { AddComponent } from './pages/add/add.component';
import { UpdateComponent } from './pages/update/update.component';

const routes: Routes = [
  {
    path: '', component: ViewAllComponent
  },
  {
    path: 'add', component: AddComponent
  },
  {
    path: 'update/:id', component: UpdateComponent
  },
  {
    path: ':id', component: ViewSpecificComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CommRoutingModule { }
