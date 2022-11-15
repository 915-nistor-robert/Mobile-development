import { Injectable } from '@angular/core';
import {AngularFireDatabase} from "@angular/fire/compat/database";

export interface Account {
  platform: string;
  username: string;
  password: string;
  id: number;
}

@Injectable({
  providedIn: 'root'
})
export class DataService {
  public accounts: Account[] = [];

  private nextId: number = 0;

  constructor(private db: AngularFireDatabase) {
    let account1: Account = {
      id: this.getNextId(),
      platform: 'Facebook',
      username: 'drake123',
      password: 'myPass',
    }
    this.addAccount(account1);

    let account2: Account = {
      id: this.getNextId(),
      platform: 'Instagram',
      username: 'robertnistor',
      password: 'mypass123',
    }
    this.addAccount(account2)

    let account3: Account = {
      id: this.getNextId(),
      platform: 'SuperBet',
      username: 'drakee',
      password: 'password',
    }
    this.addAccount(account3)


}

  public getAccounts(): Account[] {
    return this.accounts;
  }

  public getAccountById(id: number): Account {
    let product = this.accounts.find(elem => elem.id == id)
    if (product)
      return product
    else
      return null
  }

  public getNextId(): number {
    return this.nextId;
  }

  public getPositionById(id: number) : number{
    for(let i=0; i<this.accounts.length; i++){
      if(this.accounts[i].id == id)
        return i;
    }
    return -1;
  }

  public addAccount(account: Account) {
    this.accounts.push(account);
    this.nextId++;
  }

  public deleteAccount(id: number) {
    let posToDelete = this.getPositionById(id);
    this.accounts.splice(posToDelete, 1)
  }

  public updateAccount(account: Account) {
    let posToUpdate = this.getPositionById(account.id)
    this.accounts[posToUpdate] = account;
  }
}
