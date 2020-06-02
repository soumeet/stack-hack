import { Component, OnInit } from '@angular/core';
import { TaskService } from '../services/task.service';
import { Task } from '../models/task';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  currentDate: Date = new Date();
  taskNameInvalid: boolean = false;
  taskName: string = "";
  dueDate: Date = new Date();
  taskList: Task[];
  dataSource: MatTableDataSource<Task>;
  displayedColumns: string[] = ['taskName', 'dueDate'];
  constructor(private taskService: TaskService) { 
    this.dataSource = new MatTableDataSource<Task>();
  }

  ngOnInit() {
    this.taskService.getTasks().subscribe(
      res => {
        this.taskList = res as Task[];
        console.log('task-found: ', this.taskList.length);
        this.taskList = this.sortTasks(this.taskList, 'dueDate');
        this.dataSource.data = this.taskList;
      }
    );
  }

  onSubmit() {
    // console.log('task-add: onSubmit');
    let newTask: Task = {
      taskId: null,
      taskName: this.taskName,
      dueDate: this.dueDate
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
        this.addTask(addedTask);
        this.resetForm();
      }   
    );
  }

  addTask(newTask: Task) {
    // console.log('app-task: addTask', newTask);
    this.taskList.push(newTask);
    this.taskList = this.sortTasks(this.taskList, 'dueDate');
    this.dataSource.data = this.taskList;
  }

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

}
