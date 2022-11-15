import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ViewProductPageRoutingModule } from './view-account-routing.module';

import { ViewAccountPage } from './view-account-page.component';
import {HomePageModule} from "../home/home.module";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ViewProductPageRoutingModule,
    HomePageModule
  ],
  declarations: [ViewAccountPage]
})
export class ViewProductPageModule {}
