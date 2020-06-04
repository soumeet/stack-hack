import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { TaskService } from 'src/app/services/task.service';
import { Task } from 'src/app/models/task';

@Component({
  selector: 'app-tasks-add',
  templateUrl: './tasks-add.component.html',
  styleUrls: ['./tasks-add.component.css']
})
export class TasksAddComponent implements OnInit {

  @Output() onAdd = new EventEmitter<{}>();
  currentDate: Date = new Date();
  taskName: string = "";
  dueDate: Date = new Date();
  constructor(private taskService: TaskService) { }

  ngOnInit() {
    // console.log('task-add: ngOnInit');
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
      console.error('not valid');
    }
  }

  validate(newTask: Task): boolean {
    // console.log('task-add: validate', newTask);
    let res: boolean = false;
    if(newTask.taskName != '')
      if(newTask.dueDate != null)
        res = true;
    return res;
  }

  save(newTask: Task) {
    // console.log('task-add: save', newTask);
    this.taskService.addTask(newTask).subscribe(
      res => {
        // console.log('response', res);
        let addedTask = res as Task;
        this.onAdd.emit(addedTask);
      }   
    );
  }

}
