import { Component, OnInit } from '@angular/core';

import {UserService} from '../user.service';

@Component({
  moduleId: module.id,
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  model: any = {};
  flag : boolean =false;
 registerCheck : boolean = false;

  constructor(private userService: UserService) { }


  registerUser() {

    this.userService.createUser(this.model).subscribe(

      res => {

        if(res!=null)
        {

          this.flag=true;

        }
      },
      err => {
        console.log(err)
        this.registerCheck=true;
      }
    );

  }



  ngOnInit() {
  }




}
