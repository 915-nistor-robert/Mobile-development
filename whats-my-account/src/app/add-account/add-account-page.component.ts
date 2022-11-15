import { Component, OnInit } from '@angular/core';
import {ValidatorService} from "../services/validator.service";
import {DataService, Account} from "../services/data.service";
import {ProductService} from "../services/product.service";
import {NavController} from "@ionic/angular";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account-page.component.html',
  styleUrls: ['./add-account-page.component.scss'],
})
export class AddAccountPage implements OnInit {
  public platform: string | undefined;
  public username: string | undefined;
  public password: string | undefined;
  public notFilledError: boolean = false;
  public invalidDataError: boolean = false

  constructor(private validator: ValidatorService,
              private productService: ProductService,
              private router: Router) { }

  ngOnInit() {
  }

  getBackButtonText() {
    const win = window as any;
    const mode = win && win.Ionic && win.Ionic.mode;
    return mode === 'ios' ? 'Inbox' : '';
  }


  onSaveButtonPressed() {
    this.notFilledError = this.platform == '' || this.platform == undefined ||
      this.username == '' || this.username == undefined || this.password == ''
      || this.password == undefined


    if (this.notFilledError || this.invalidDataError)
      return;

    if(this.platform && this.password && this.username) {
      this.productService.addAccount(this.platform,this.username, this.password)
      this.router.navigate(['/'])
    }
  }
}
