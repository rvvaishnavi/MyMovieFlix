import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import {ActivatedRoute} from '@angular/router';
import {MoviesService} from '../movies.service';

@Component({
  selector: 'app-addmovie',
  templateUrl: './addmovie.component.html',
  styleUrls: ['./addmovie.component.css']
})
export class AddmovieComponent implements OnInit {

  currentUser: User;
  model: any = {};
  flag: boolean = false;
  constructor(private moviesService: MoviesService) {

    this.currentUser = (<User> JSON.parse(localStorage.getItem('currentUser')));
    console.log(this.currentUser.firstName);
  }

  addMovie()
  {
    console.log("add movie");

    this.moviesService.addMovie(this.model).subscribe(

      res => {

        if(res!=null)
        {

          console.log("movie added");
          this.flag = true;
        }
      },
      err => console.log(err)

    );

  }

  ngOnInit() {
  }

}
