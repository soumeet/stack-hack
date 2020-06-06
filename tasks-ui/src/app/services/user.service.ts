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

  getUsers() {
    let url = this.contextPath + 'all';
    console.log('task.service:', url);
    return this.http.get(url);
  }

  addUser(newUser: User) {
    let result: any;
    let url = this.contextPath + 'add';
    console.log('task.service:', url);
    return this.http.post(url, newUser);
  }

  updateUser(updateUser: User) {
    let result: any;
    let url = this.contextPath + 'update';
    console.log('task.service:', url);
    return this.http.post(url, updateUser);
  }
}
