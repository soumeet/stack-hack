import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  contextPath = environment.baseUrl + 'task';
  constructor(private http: HttpClient) { }

  getTasks() {
    let url = this.contextPath + '/';
    console.log('task.service:',url);
    return this.http.get(url);
  }
}
