import { Injectable } from '@angular/core';


import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';


@Injectable()
export class TopratedMoviesService {

  constructor(private http: Http){}


  getTopRatedMovies():Observable<any[]> {
    let url = "http://localhost:8888/user/getTopRatedMovies";
    return this.http.get(url)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }


  getTopRatedSeries():Observable<any[]> {
    let url = "http://localhost:8888/user/getTopRatedSeries";
    return this.http.get(url)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }


}
