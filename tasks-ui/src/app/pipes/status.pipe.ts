import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'status'
})
export class StatusPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    let res: string = null;
    if(value === 0)
      res = 'New';
    else if(value === 1)
      res = 'In-Progress';
    else if(value === 2)
      res = 'Completed';
    return res;
  }

}
