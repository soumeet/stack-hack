import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { User } from '../models/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private userService: UserService
  ) {
    this.loginForm =  this.formBuilder.group({
      userName: new FormControl('', [
        Validators.required, 
        Validators.minLength(4), 
        Validators.maxLength(10),
      ]),
      password: new FormControl('', [
        Validators.required, 
        Validators.minLength(4), 
        Validators.maxLength(8)])
    });
  }

  ngOnInit() {

  }

  onSubmit(loginForm: FormGroup) {
    // console.log('app-login: onSubmit()', loginForm);
    let loginUser: User = {
      userId: null,
      userName: loginForm.controls.userName.value,
      password: loginForm.controls.password.value
    }
    if(this.validate(loginUser))
      this.authenticate(loginUser);
    else
      console.error('app-login: onSubmit()', 'invalid login-info');
    // this.loginSuccess();
  }

  validate(loginUser: User): boolean {
    // console.log('app-login: validate()', loginUser);
    let res: boolean = false;
    if(loginUser.userName != '' && loginUser.password != '')
      res= true;
    return res;
  }

  authenticate(loginUser: User) {
    // console.log('app-login: authenticate()', loginUser);
    this.userService.authenticateUser(loginUser).subscribe(
      res => {
        // console.log(res);
        let responseCode = res as number;
        if(responseCode == 0) {
          console.error('app-login: authenticate()', 'user doesnot exist');
          this.loginForm.controls.userName.setErrors({'notfound': true});
        } else if(responseCode == 1) {
          console.error('app-login: authenticate()', 'wrong password');
          this.loginForm.controls.password.setErrors({'wrong': true});
        } else if(responseCode == 2) {
          this.requestUserDetails(loginUser);
          this.loginForm.reset();
        }
      },
      err => {
        console.error(err);
      }
    )
  }

  requestUserDetails(loginUser: User) {
    // console.log('app-login: requestUserDetails()', loginUser);
    this.userService.getUser(loginUser).subscribe(
      res => {
        console.log(res);
        let loggedInUser = res as User;
        console.log('app-login requestUserDetails: loggedIn userId:', loggedInUser.userId);
        sessionStorage.setItem('USER', JSON.stringify(loggedInUser));
        this.router.navigateByUrl('/tasks');
      },
      err => {
        console.error(err);
      }
    );
  }

  loginSuccess() {
    console.log('app-login: loginSuccess()', 'navigate to tasks');
    this.router.navigateByUrl('/tasks');
  }

  getErrorMessage(field: string) {
    // console.log('app-login', field, this.loginForm.get(field));
    if (this.loginForm.get(field).hasError('required'))
      return 'You must enter a value';
    if (this.loginForm.get(field).hasError('minlength')) 
      return 'Minimum length ' +  this.loginForm.get(field).getError('minlength')['requiredLength'];
    if (this.loginForm.get(field).hasError('maxlength')) 
      return 'Maximum length ' +  this.loginForm.get(field).getError('maxlength')['requiredLength'];
    if(field == 'userName') {
      console.log(field, this.loginForm.get(field).getError('notfound'));
      if(this.loginForm.get(field).getError('notfound'))
        return 'User Name do not exists';
    }
    if(field == 'password') {
      console.log(field, this.loginForm.get(field).getError('wrong'));
      if(this.loginForm.get(field).getError('wrong'))
        return 'Wrong Password';
    }
    return this.loginForm.get(field).hasError(field) ? 'Not a valid ' + field : '';

  }
}
