import { Component, OnInit } from '@angular/core';
import {DataService, Account} from "../services/data.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AlertController, NavController} from "@ionic/angular";
import {ProductService} from "../services/product.service";
import {ValidatorService} from "../services/validator.service";

@Component({
  selector: 'app-view-account',
  templateUrl: './view-account-page.component.html',
  styleUrls: ['./view-account-page.component.scss'],
})
export class ViewAccountPage implements OnInit {
  public account: Account | undefined;
  public username: string;
  public password: string;
  public notFilledError: boolean = false;
  public invalidDataError: boolean = false

  constructor(private data: DataService,
              private activatedRoute: ActivatedRoute,
              private alertController: AlertController,
              private productService: ProductService,
              private validator: ValidatorService,
              private router: Router) { }

  private id: string;

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.paramMap.get('id') as string;
    console.log(this.id)
    this.account = this.data.getAccountById(parseInt(this.id, 10));
  }

  getBackButtonText() {
    const win = window as any;
    const mode = win && win.Ionic && win.Ionic.mode;
    return mode === 'ios' ? 'Inbox' : '';
  }

  async onDeleteButtonPressed() {
    const alert = await this.alertController.create({
      header: 'Are you sure you want to delete this account?',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
        },
        {
          text: 'Yes',
          role: 'confirm',
          handler: () => {
            this.productService.deleteAccount(this.id);
            this.router.navigate(['/'])
          },
        },
      ],
    });

    await alert.present();
  }

  onUpdateButtonPressed() {
    this.notFilledError = this.password == '' || this.password == undefined ||
      this.username == '' || this.username == undefined



    if (this.notFilledError || this.invalidDataError)
      return;

    if(this.password && this.username) {
      this.productService.updateAccount(this.id, this.password, this.username)
      this.router.navigate(['/'])
    }
  }
}
