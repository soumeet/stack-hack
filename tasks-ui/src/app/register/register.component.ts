import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../models/user';
import { FormGroup, FormBuilder, Validators, FormControl, ValidationErrors, ValidatorFn, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userForm: FormGroup;
  userList: string[];
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private authService: AuthService) { 
    this.userForm =  this.formBuilder.group({
      userName: new FormControl('', [
        Validators.required, 
        Validators.minLength(4), 
        Validators.maxLength(10),,
        this.checkDuplicate()
      ]),
      password: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(8)]),
      confirmPassword: new FormControl('', [
        Validators.required,
        Validators.minLength(4), 
        Validators.maxLength(8), 
        this.checkMatch()
      ])
    });
  }

  ngOnInit() {
    this.userService.getUsernames().subscribe(
      res => {
        // console.log(res);
        this.userList = res as string[];
      },
      err => {
        console.error(err);
      }
    )
  }

  onSubmit(newUserForm: FormGroup) {
    // console.log('app-register: onSubmit', newUserForm);
    let newUser: User = {
      userId: null,
      userName: newUserForm.controls.userName.value,
      password: newUserForm.controls.password.value
    };
    if(this.validate(newUser))
      this.save(newUser);
    else {
      console.error('onSubmit: userForm not valid');
    }
  }

  validate(newUser: User): boolean {
    // console.log('app-register: validate', newUser);
    let res: boolean = false;
    if(newUser.password != '' && newUser.userName != '')
        res = true;
    return res;
  }

  save(newUser: User) {
    // console.log('app-register: save', newUser);
    this.userService.addUser(newUser).subscribe(
      res => {
        // console.log('response', res);
        let addedUser = res as User;
        console.log('app-user save: added userId:', addedUser.userId);
        // this.userForm.reset();
        // sessionStorage.setItem('USER', JSON.stringify(addedUser));
        this.authService.saveUser(addedUser);
        this.router.navigateByUrl('/tasks');
      }   
    );
  }

  checkMatch(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } | null => {
      if (control.value !== undefined && (isNaN(control.value)))
        if (this.userForm.controls.confirmPassword.value != this.userForm.controls.password.value) {
          return { 'match': false };
        }
      return null;
    };
  }

  checkDuplicate(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } | null => {
      if (control.value !== undefined && (isNaN(control.value)))
        if (this.userList.indexOf(control.value) != -1) {
          return { 'duplicate': true };
        }
      return null;
    };
  }

  getErrorMessage(field: string) {
    // console.log('app-register', field, this.userForm.get(field));
    if (this.userForm.get(field).hasError('required'))
      return 'You must enter a value';
    if (this.userForm.get(field).hasError('minlength')) 
      return 'Minimum length ' +  this.userForm.get(field).getError('minlength')['requiredLength'];
    if (this.userForm.get(field).hasError('maxlength')) 
      return 'Maximum length ' +  this.userForm.get(field).getError('maxlength')['requiredLength'];
    if(field == 'userName') {
      if(!this.userForm.get(field).getError['duplicate'])
        return 'User Name already exists';
    }
    if(field == 'confirmPassword') {
      if(!this.userForm.get(field).getError['match'])
        return 'Password should match';
    }
    return this.userForm.get(field).hasError(field) ? 'Not a valid ' + field : '';
  }
}
