import { Component, OnInit } from '@angular/core';

import {MoviesService} from '../movies.service';
import { User } from '../model/user';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {


  moviesList: any = [];
  currentUser: User;
  username : string;
  flag : boolean = false;
  adminCheck : boolean = false;

  constructor(private moviesService: MoviesService,private router:Router) {

    this.currentUser = (<User> JSON.parse(localStorage.getItem('currentUser')));
    if(this.currentUser.username=="admin@gmail.com")
    {
      this.adminCheck=true;

    }
    moviesService.getMovies()
      .subscribe(
        movies => this.moviesList = movies,
        error => console.log(error)
      );

  }

  sortMoviesBy(id: string)
  {
    this.moviesService.sortMoviesBy(id).subscribe(
      movies => this.moviesList = movies);
  }

  searchMoviesByYear(year)
  {

     this.moviesService.searchMoviesByYear(year).subscribe(
      movies => this.moviesList = movies
    );

  }

  searchMoviesByType(type)
  {

    this.moviesService.searchMoviesByType(type).subscribe(
      movies => this.moviesList = movies
    );

  }


  searchMoviesByGenre(genre)
  {

    this.moviesService.searchMoviesByGenre(genre).subscribe(
      movies => this.moviesList = movies
    );

  }

  deleteMovie(id:number)
  {

    this.moviesService.deleteMovie(id).subscribe(
      movies => {
        this.router.navigate(['/movies']);
      }
    );


  }

  editMovie(id:number)
  {


    this.router.navigate(['movies/'+id]);
    localStorage.setItem('edit',"true");
  }

  ngOnInit() {
  }

}
