import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MoviesComponent } from './movies/movies.component';
import { routing }  from './app.routing';

import { LoginService } from './login.service';
import { UserService } from './user.service';
import { MoviesService } from './movies.service';
import { MovieComponent } from './movie/movie.component';

import {TopratedMoviesService} from "./toprated-movies.service";
import { TopRatedMoviesComponent } from './top-rated-movies/top-rated-movies.component';
import { TopRatedSeriesComponent } from './top-rated-series/top-rated-series.component';

import { AddmovieComponent } from './addmovie/addmovie.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MoviesComponent,
    MovieComponent,
    TopRatedMoviesComponent,
    TopRatedSeriesComponent,

    AddmovieComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [LoginService,MoviesService,UserService,TopratedMoviesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
