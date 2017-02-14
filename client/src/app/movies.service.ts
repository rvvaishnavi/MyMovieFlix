import { Injectable } from '@angular/core';


import { Http , Headers , RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';


@Injectable()
export class MoviesService {

  constructor(private http: Http){}



  getMovies(): Observable<any[]> {
    let url = "http://localhost:8888/movie/movieList";
    return this.http.get(url)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }


  getMovieById(id: number): Observable<any[]> {

    return this.http.get(`http://localhost:8888/movie/${id}`)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }



  sortMoviesBy(id: string):Observable<any[]> {

    return this.http.get(`http://localhost:8888/user/sortBy/${id}`)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }

  searchMoviesByYear(id: string):Observable<any[]> {

    return this.http.get(`http://localhost:8888/user/filterByYear/${id}`)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }

  searchMoviesByType(id: string):Observable<any[]> {

    return this.http.get(`http://localhost:8888/user/filterByType/${id}`)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }

  searchMoviesByGenre(id: string):Observable<any[]> {

    return this.http.get(`http://localhost:8888/user/filterByGenre2/${id}`)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }

  rateTheMovie(ratings: any) : Observable<any[]> {
    let headers = new Headers({ 'Content-Type': 'application/json', "Access-Control-Allow-Origin" : "*" });
    let options = new RequestOptions({ headers: headers });
    return this.http.post('http://localhost:8888/user/rateTheMovie', ratings, options)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }


  editTheMovie(movie: any) : Observable<any[]> {
    let headers = new Headers({ 'Content-Type': 'application/json', "Access-Control-Allow-Origin" : "*" });
    let options = new RequestOptions({ headers: headers });
    return this.http.put('http://localhost:8888/admin/movie/update2', movie , options)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }

  getMovieRatingsAndComments(id: number):Observable<any[]> {

    return this.http.get(`http://localhost:8888/user/getMovieRatings/${id}`)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }


  getMovieAvgRating(id: number):Observable<any[]> {

    return this.http.get(`http://localhost:8888/user/getMovieAvgRating/${id}`)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }


  deleteMovie(id: number):Observable<any[]> {

    return this.http.delete(`http://localhost:8888/admin/movie/delete/${id}`)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }


  addMovie(movie: any) : Observable<any[]> {
    let headers = new Headers({ 'Content-Type': 'application/json', "Access-Control-Allow-Origin" : "*" });
    let options = new RequestOptions({ headers: headers });
    return this.http.post('http://localhost:8888/admin/movie/create', movie, options)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }


}
