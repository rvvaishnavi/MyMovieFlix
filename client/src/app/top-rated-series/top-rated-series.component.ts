import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import {TopratedMoviesService} from "../toprated-movies.service";


@Component({
  selector: 'app-top-rated-series',
  templateUrl: './top-rated-series.component.html',
  styleUrls: ['./top-rated-series.component.css']
})
export class TopRatedSeriesComponent implements OnInit {

  moviesList: any = [];
  currentUser: User;
  username : string;
  caption: string;

  constructor(private topRatedMoviesService: TopratedMoviesService) {

    this.currentUser = (<User> JSON.parse(localStorage.getItem('currentUser')));
    this.username = this.currentUser.firstName;
    this.caption ="Top Rated Series";
    topRatedMoviesService.getTopRatedSeries()
      .subscribe(
        movies => this.moviesList = movies,
        error => console.log(error)
      );


  }

  ngOnInit() {
  }

}
