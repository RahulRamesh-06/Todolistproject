import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SecurityService } from 'src/app/services/security/security.service';
import {userdetails} from  'src/app/components/userdetails'
import { Router } from '@angular/router';
@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  @ViewChild('signinForm', { static: false })
  signinForm: NgForm;

  username: string = null;
  password: string = null;

  constructor(private securityService: SecurityService,private Router:Router) { }

  ngOnInit() {
  }

  /**
   * Validate user details and rediect user to dashboard
   */

  user:userdetails={
    username:'',
    password:''
  };
  validateUser() {
   
    this.user.username=this.signinForm.value.username;
    this.user.password=this.signinForm.value.password;

    this.securityService.updateCredentials(this.user);
    // this.securityService.validateUser();
  }

  singup() {
    console.log('singup testing')
    this.Router.navigateByUrl('singup')
  }

}
