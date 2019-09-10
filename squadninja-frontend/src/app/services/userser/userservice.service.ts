import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {


  constructor(private http: HttpClient) {
    this.http = http;
  }

  // tslint:disable-next-line:adjacent-overload-signatures
  Login(email: string, password: string) {

    console.log({email:email, password: password})
    let  obj = {
      email: email,
      password: password
    }
    // this.http.get("http://localhost:8080/hello", {responseType: 'text'}).subscribe(console.log)
    return this.http.post(`http://localhost:8085/api/v1/user`, { email: email, password: password }, HttpHeaders {[header:String]:String});

  }
}

