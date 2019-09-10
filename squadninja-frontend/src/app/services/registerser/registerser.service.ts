import { Injectable } from '@angular/core';
import { IhprofileserService } from '../ihprofileser/ihprofileser.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterserService {

  public role: string="serviceprovider";

  private _url: string = "http://localhost:8081/api/v1/serviceprovider";
  constructor(private ihprofileser: IhprofileserService,private http:HttpClient) { }
}
