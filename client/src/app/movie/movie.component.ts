import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {MoviesService} from '../movies.service';
import { User } from '../model/user';


@Component({
  moduleId: module.id,
  selector: 'app-movie',
  templateUrl: 'movie.component.html',
  styleUrls: ['movie.component.css']
})
export class MovieComponent implements OnInit {

  model: any = {};
  currentUser: User;
  flag : boolean =false;
  mymovie: any = [];
  check : boolean = false;
  ratingsList: any = [];
  avgRating : any [];
 adminCheck: boolean = false;
 edit: string=null;
 editCheck: boolean = false;
 msg:string;

  constructor(private route: ActivatedRoute, private moviesService: MoviesService) {
    this.currentUser = (<User> JSON.parse(localStorage.getItem('currentUser')));
    this.edit = localStorage.getItem('edit');
    if(this.currentUser.username=="admin@gmail.com")
    {
      this.adminCheck=true;

      if(this.edit=="true") {
        this.editCheck = true;
        localStorage.removeItem("edit");
      }
    }
    this.route.params.subscribe(
      params => {
        moviesService.getMovieById(params['id'])
          .subscribe(

            movies => this.mymovie = movies,

            error => console.log(error)
          );
      });

  }

  submitRatingComment()
  {


    this.model.userId = this.currentUser.userId;
    this.model.movieId = this.mymovie.movieId;


   this.moviesService.rateTheMovie(this.model).subscribe(

     res => {

       if(res!=null)
       {

         this.flag=true;

       }
     },
     err => console.log(err)

   );


  }


  getRatingComment()
  {


    this.model.userId = this.currentUser.userId;
    this.model.movieId = this.mymovie.movieId;


    this.moviesService.getMovieRatingsAndComments(this.model.movieId).subscribe(

     movies => this.ratingsList = movies

    );
    this.moviesService.getMovieAvgRating(this.model.movieId).subscribe(

      avgRating => this.avgRating = avgRating

    );


    if(!this.ratingsList.isEmpty)
      this.check=true;

  }

  editMovie()
  {

    this.model.movieId = this.mymovie.movieId;

    this.moviesService.editTheMovie(this.model).subscribe(
      res => {
        if(res!=null)
        {
          console.log("hey"+JSON.stringify(res));
         this.msg="edited successfully";
        }
      }
    );
  }

  ngOnInit() {
  }

}
