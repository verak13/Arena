import { AbstractControl, ValidatorFn } from '@angular/forms';


export function validateMatchPassword(field1: string | (string | number)[], field2: string | (string | number)[]): ValidatorFn {
  return (c: AbstractControl): {[key: string]: any} | null => {
        // @ts-ignore
    const field1ToCompare = c.get(field1) ? c.get(field1).value : '';
        // @ts-ignore
    const field2ToCompare = c.get(field2) ? c.get(field2).value : '';
        const isEqual = field1ToCompare === field2ToCompare;

        return isEqual ? null : {testEqual: {valid: false}};
  };
}
