import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidatorService {

  constructor() { }

  onlyLettersAndSpaces(input: string | undefined) {
    if (input == undefined)
      return false
    return /^[A-Za-z\s]*$/.test(input);
  }

  onlyNumbers(input: string | undefined) {
    if (input == undefined)
      return false
    return /^[0-9]*$/.test(input);
  }

  isItADate(input: string | undefined) {
    if (input == undefined)
      return false
    return /^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[13-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$/.test(input);
  }

  isMeasurement(input: string | undefined) {
    if (input == undefined)
      return false
    return input == 'pcs' || input == 'g' || input == 'ml';
  }

  isPrice(input: string | undefined) {
    if(input == undefined)
      return false
    return /^\d+(,\d{1,2})?$/.test(input)
  }
}
