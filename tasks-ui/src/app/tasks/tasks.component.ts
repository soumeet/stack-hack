import { Component, OnInit } from '@angular/core';
import { TaskService } from '../services/task.service';
import { Task } from '../models/task';
import { MatTableDataSource } from '@angular/material';
import { Label } from '../models/label';
import { Status } from '../models/status';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  currentDate: Date = new Date();
  taskNameInvalid: boolean = false;
  taskName: string = "";
  labelCode: number = 4;
  statusCode: number = 2;
  dueDate: Date = new Date();
  taskList: Task[];
  dataSource: MatTableDataSource<Task>;
  displayedColumns: string[];
  labels: Label[];
  statuses: Status[];
  filterType: string = "taskName";
  filterTypes: any;
  constructor(private taskService: TaskService) { 
    this.dataSource = new MatTableDataSource<Task>();
    this.displayedColumns = ['taskName', 'labelCode', 'dueDate', 'statusCode'];
    this.labels = [
      { labelCode: 1, labelValue: 'Personal' },
      { labelCode: 2, labelValue: 'Work' },
      { labelCode: 3, labelValue: 'Shopping' },
      { labelCode: 4, labelValue: 'Others' }
    ];
    this.statuses = [
      { statusCode: 0, statusValue: 'New' },
      { statusCode: 1, statusValue: 'In-Progress' },
      { statusCode: 2, statusValue: 'Completed' }
    ];
    this.filterTypes = {
      'taskName': 'Task Name',
      'dueDate': 'Due Date',
      'labelCode': 'Label',
      'statusCode': 'Status'
    };
  }

  ngOnInit() {
    this.taskService.getTasks().subscribe(
      res => {
        // console.log('getTasks(): ', res);
        this.taskList = res as Task[];
        console.log('task-found: ', this.taskList.length);
        this.taskList = this.sortTasks(this.taskList, 'dueDate');
        this.dataSource.data = this.taskList;
      },
      err => {
        console.log('getTasks(): ', err);
      }
    );
  }

  onSubmit() {
    // console.log('app-task: onSubmit');
    let newTask: Task = {
      taskId: null,
      taskName: this.taskName,
      dueDate: this.dueDate,
      labelCode: this.labelCode,
      statusCode: 0
    };
    if(this.validate(newTask))
      this.save(newTask);
    else {
      console.error('onSubmit: taskName not valid');
    }
  }

  validate(newTask: Task): boolean {
    // console.log('task-add: validate', newTask);
    let res: boolean = false;
    if(newTask.dueDate != null)
      if(newTask.taskName != '')
        res = true;
    this.taskNameInvalid = !res;
    return res;
  }

  save(newTask: Task) {
    // console.log('task-add: save', newTask);
    this.taskService.addTask(newTask).subscribe(
      res => {
        // console.log('response', res);
        let addedTask = res as Task;
        console.log('app-task save: added taskId:', addedTask.taskId);
        this.addTask(addedTask);
        this.resetForm();
      }   
    );
  }

  update(updateTask: Task) {
    // console.log('task: update', updateTask);
    this.taskService.updateTask(updateTask).subscribe(
      res => {
        // console.log('response', res);
        console.log('app-task update: updated taskId:', updateTask.taskId);
      },
      err => {
        console.log('response', err);
        // this.revertUpdate(updateTask);
      }   
    );
  }

  addTask(newTask: Task) {
    // console.log('app-task: addTask', newTask);
    this.taskList.push(newTask);
    this.taskList = this.sortTasks(this.taskList, 'dueDate');
    this.dataSource.data = this.taskList;
  }

  /*updateTask(updatedTask: Task) {
    // console.log('app-task: addTask', newTask);
    this.taskList.push(updatedTask);
    this.taskList = this.sortTasks(this.taskList, 'dueDate');
    this.dataSource.data = this.taskList;
  }*/

  resetForm(): void {
    this.dueDate = this.currentDate;
    this.taskName = '';
  }

  sortTasks(taskList: Task[], field: string): Task[]{
    if(field === 'dueDate')
      this.taskList.sort((a: Task, b: Task) => {
        if (a.dueDate < b.dueDate)
            return -1;
        if (a.dueDate > b.dueDate)
            return 1;
        return 0;
      });
    return taskList;
  }

  applyFilter(event: Event, filterType: string) {
    // console.log('app-task: filter:', filterType, event);
    let filterValue: any;
    if(filterType == 'taskName') 
      filterValue = (event.target as HTMLInputElement).value;
    else if(filterType == 'labelCode' || filterType == 'statusCode')
      filterValue = event['value'];
    else {
      filterValue = event['value'];
    }
    // console.log('app-task: filter:', filterType, filterValue);

    this.dataSource.filterPredicate = (data: Task, filter) => {
      // console.log('app-task: filterPredicate:', data, filter);
      if(filterType == 'taskName')
        return data[filterType].indexOf(filter) != -1;
      else if(filterType == 'labelCode' || filterType == 'statusCode')
        return data[filterType].toString()===filter;
      else if(filterType == 'startDate' || filterType == 'endDate'){
        let dueDateValue = new Date(data['dueDate']).getTime();
        let filterDateValue = new Date(filter).getTime();
        // console.log(filterType, dueDateValue, filterDateValue)
        if(filterType == 'startDate')
          return dueDateValue > filterDateValue;
        else if(filterType == 'endDate') {
          filterDateValue += 86400000;
          return dueDateValue <= filterDateValue;
        }
      }
    };
    this.dataSource.filter = filterValue;
  }
} 
