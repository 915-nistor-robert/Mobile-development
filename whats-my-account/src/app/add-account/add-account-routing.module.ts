import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddAccountPage } from './add-account-page.component';

const routes: Routes = [
  {
    path: '',
    component: AddAccountPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddProductPageRoutingModule {}
