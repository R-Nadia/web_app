import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'search-users',
  templateUrl: './search-users.component.html',
  styleUrls: ['./search-users.component.css']
})
export class SearchUsersComponent implements OnInit {

  age: number;
  users: User[];

  constructor(private dataService: UserService) { }

  ngOnInit() {
    this.age = 0;
  }

  private searchUsers() {
    this.dataService.getUsersByAge(this.age)
      .subscribe(users => this.users = users);
  }

  onSubmit() {
    this.searchUsers();
  }

}
