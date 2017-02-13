import { Injectable } from '@angular/core';

import { Http, Headers} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

@Injectable()
export class LoginService {

  constructor (private http: Http) {


  }

  sendCredential(username: string, password: string) {
    let url = "http://localhost:8888/loginCheck?username="+username+"&password="+password;
   /* let headers = new Headers(
      {
        'Content-Type': 'application/x-www-form-urlencoded',

        "Access-Control-Allow-Origin" : "*"
      });*/
      return this.http.get(url)
      .map(response => response.text())
      .catch(error => Observable.throw(error.statusText));;
  }




}
