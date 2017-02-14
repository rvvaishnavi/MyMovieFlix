import { Injectable } from '@angular/core';


import { Http , Headers , RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

@Injectable()
export class UserService {

  constructor(private http: Http){}

  createUser(user: any): Observable<any[]> {
    let headers = new Headers({ 'Content-Type': 'application/json', "Access-Control-Allow-Origin" : "*" });
    let options = new RequestOptions({ headers: headers });
    return this.http.post('http://localhost:8888/register', user, options)
      .map(response => response.json())
      .catch(error => Observable.throw(error.statusText));
  }


}
