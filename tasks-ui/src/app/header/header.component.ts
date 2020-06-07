import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  appTitle: string = 'Tasks';
  userName: string = 'USER_NAME';
  isLoggedIn: Observable<boolean>;
  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    console.log('app-header: ngOnInit()');
    this.isLoggedIn = this.authService.isLoggedIn;
    this.isLoggedIn.subscribe(
      res => {
        // console.log(res);
        let loggedIn = res as boolean;
        if(loggedIn) {
          // console.log('app-header: ngOnInit()', 'navigate to tasks');
          let user = this.authService.loggedInUser;
          this.userName = user.userName;
          this.router.navigate(['tasks']);
        }
        else {
          // console.log('app-header: ngOnInit()', 'navigate to login');
          this.router.navigate(['login']);
        }
      },
      err => {
        console.error(err);
      }
    );
  }

  logout() {
    console.log('app-header: logout()');
    this.authService.removeUser();
    this.router.navigate(['login']);
  }
}
