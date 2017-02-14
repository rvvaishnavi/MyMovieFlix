/**
 * Created by vaishnavirachapudi on 2/3/17.
 */

import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';

import {RegisterComponent} from './register/register.component'

import { MoviesComponent } from './movies/movies.component';

import { MovieComponent } from './movie/movie.component';

import { AddmovieComponent } from './addmovie/addmovie.component';

import {TopRatedMoviesComponent} from './top-rated-movies/top-rated-movies.component'

import {TopRatedSeriesComponent} from './top-rated-series/top-rated-series.component'

const appRoutes: Routes = [
  { path: '', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'movies', component: MoviesComponent},
  { path: 'topRatedMovies', component: TopRatedMoviesComponent},
  { path: 'topRatedSeries', component: TopRatedSeriesComponent},
  { path: 'addMovie', component: AddmovieComponent},
  {path: 'movies/:id', component: MovieComponent},
  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
