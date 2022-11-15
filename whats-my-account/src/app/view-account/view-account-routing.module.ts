import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ViewAccountPage } from './view-account-page.component';

const routes: Routes = [
  {
    path: '',
    component: ViewAccountPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ViewProductPageRoutingModule {}
