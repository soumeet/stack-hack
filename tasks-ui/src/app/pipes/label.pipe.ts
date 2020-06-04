import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'label'
})
export class LabelPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    let res: string = null;
    if(value === 1)
      res = 'Personal';
    else if(value === 2)
      res = 'Work';
    else if(value === 3)
      res = 'Shopping';
    else if(value === 4)
      res = 'Others';
    return res;
  }

}
