import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AddProductPageRoutingModule } from './add-account-routing.module';

import { AddAccountPage } from './add-account-page.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AddProductPageRoutingModule
  ],
  declarations: [AddAccountPage]
})
export class AddProductPageModule {}
