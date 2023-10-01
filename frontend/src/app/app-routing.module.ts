import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '@core/pages/home/home.component';
import { PageNotFoundComponent } from '@core/pages/page-not-found/page-not-found.component';

const routes: Routes = [
  {
    path: '', pathMatch: 'full',
    component: HomeComponent
  },
  {
    path: 'comm_class',
    loadChildren: () => import('./comm-class/comm-class.module').then(m => m.CommClassModule)
  },
  {
    path: 'comm',
    loadChildren: () => import('./comm/comm.module').then(m => m.CommModule)
  },
  {
    path: '**', pathMatch: "full",
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
