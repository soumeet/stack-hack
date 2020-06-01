import { Component, OnInit, Input, OnChanges, SimpleChanges, SimpleChange } from '@angular/core';
import { Task } from 'src/app/models/task';

@Component({
  selector: 'app-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.css']
})
export class TasksListComponent implements OnChanges {

  @Input() dataSource: Task[];
  displayedColumns: string[] = ['taskId', 'taskName', 'dueDate'];
  constructor() { }

  ngOnChanges(changes:  {[propKey: string]: SimpleChange}): void {
    // console.log('onChanges', changes);
  }

  ngOnInit() {
  }

}
