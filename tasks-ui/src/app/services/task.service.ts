import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Task } from '../models/task';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  contextPath = environment.baseUrl + 'task';
  constructor(private http: HttpClient) { }

  getTasks() {
    let url = this.contextPath + '/all';
    console.log('task.service:', url);
    return this.http.get(url);
  }

  addTask(newTask: Task) {
    let result: any;
    let url = this.contextPath + '/add';
    console.log('task.service:', url);
    return this.http.post(url, newTask);
  }
}
