import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../services/userser/userservice.service';
import * as jwt_decode from 'jwt-decode';


import { RegisterserService } from '../services/registerser/registerser.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {



  focus;
  focus1;

  public role:any;
  public email : any;
  public password : any;
  public token : any;

  public tokenInfo = this.getDecodedAccessToken(this.token); // decode token


  constructor(private registerser:RegisterserService, private userservice : UserserviceService) { }



  ngOnInit() {
  }
  //  // show decoded token object in console


  getDecodedAccessToken(token: string): any {
    try {
        return jwt_decode(token);
    } catch (Error) {
        return null;
    }
  }

  selectIh() {
    console.log(this.tokenInfo);
    this.role = 'ideahamster';
  }

  selectSp() {
    this.role = 'serviceprovider';
  }
  // selectIh(){
  //   this.registerser.selectIh();
  // }

  Login() {
    this.userservice.Login (this.email, this.password).subscribe((response) => {
      console.log(response);
      if (response) {
        this.token = response;
        console.log(response);
  }}, (err) => {
    console.log(err);
});
}
}