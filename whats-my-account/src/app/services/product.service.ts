import { Injectable } from '@angular/core';
import {DataService, Account} from "./data.service";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(public dataService: DataService) { }

  public addAccount(platform: string, username: string, password: string) {
    let newAccount: Account = {
      id: this.dataService.getNextId(),
      platform: platform,
      username: username,
      password: password,
    }
    this.dataService.addAccount(newAccount);
  }

  public deleteAccount(id: string) {
    this.dataService.deleteAccount(parseInt(id, 10));
  }

  public updateAccount(id: string, password: string, username: string) {
    let oldAccount = this.dataService.getAccountById(parseInt(id, 10))
    let newAccount: Account = {
      id: oldAccount.id,
      platform: oldAccount.platform,
      username: username,
      password: password,
    }
    this.dataService.updateAccount(newAccount);
  }
}
