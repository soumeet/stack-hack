import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedIn: BehaviorSubject<boolean> 
  constructor() {
    this.loggedIn = new BehaviorSubject<boolean>(false);
    if(this.loggedInUser != null)
      this.loggedIn.next(true);
  }

  isAuthenticated(): boolean {
    let res: boolean = false;
    const user = this.loggedIn;
    if(user != null)
      res = true;
    return res;
  }

  saveUser(user: User) {
    // console.log('auth-service: saveUser()', user);
    sessionStorage.setItem('USER', JSON.stringify(user));
    this.loggedIn.next(true);
  }

  removeUser() {
    // console.log('auth-service: removeUser()');
    sessionStorage.clear();
    this.loggedIn.next(false);
  }

  get isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  get loggedInUser(): User {
    // console.log('auth-service: getUser()');
    let user = JSON.parse(sessionStorage.getItem('USER'));
    return user;
  }
}
