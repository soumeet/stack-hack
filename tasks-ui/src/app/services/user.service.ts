import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  contextPath = environment.userUrl;
  constructor(private http: HttpClient) { }

  getUsernames() {
    let url = this.contextPath + 'all';
    console.log('task.service:', url);
    return this.http.get(url);
  }

  getUser(user: User) {
    let url = this.contextPath + 'get';
    console.log('task.service:', url);
    return this.http.post(url, user);
  }

  addUser(newUser: User) {
    let url = this.contextPath + 'add';
    console.log('task.service:', url);
    return this.http.post(url, newUser);
  }

  authenticateUser(user: User) {
    let url = this.contextPath + 'authenticate';
    console.log('task.service:', url);
    return this.http.post(url, user);
  }
}
