import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import {TopratedMoviesService} from "../toprated-movies.service";

@Component({
  moduleId: module.id,
  selector: 'app-top-rated-movies',
  templateUrl: './top-rated-movies.component.html',
  styleUrls: ['./top-rated-movies.component.css']
})
export class TopRatedMoviesComponent implements OnInit {

  moviesList: any = [];
  currentUser: User;
  username : string;
  caption: string;

  constructor(private topRatedMoviesService: TopratedMoviesService) {

    this.currentUser = (<User> JSON.parse(localStorage.getItem('currentUser')));
    this.username = this.currentUser.firstName;
    this.caption ="Top Rated Movies";
    topRatedMoviesService.getTopRatedMovies()
      .subscribe(
        movies => this.moviesList = movies,
        error => console.log(error)
      );


  }

  ngOnInit() {
  }

}
