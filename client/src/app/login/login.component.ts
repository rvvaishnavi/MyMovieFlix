import { Component, OnInit } from '@angular/core';

import {LoginService} from '../login.service';
import {Router,ActivatedRoute} from '@angular/router';
import {MoviesService} from '../movies.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']

})





export class LoginComponent implements OnInit {

  model: any = {};
  loginFail : boolean = false;
  constructor (private route: ActivatedRoute,private loginService: LoginService,private moviesService: MoviesService,private router:Router){
    this.route.params.subscribe(
      params => {
        moviesService.getMovies()
          .subscribe(


          );

      });
  }

 onSubmit() {
    this.loginService.sendCredential(this.model.username, this.model.password).subscribe(
      res => {
           if(res!=null)
           {

             localStorage.setItem('currentUser', res);

            this.router.navigate(['/movies']);
           }
      },
      err => {
        console.log(err)
        this.loginFail=true;
      }

    );
  }




  ngOnInit() {
  }

}
