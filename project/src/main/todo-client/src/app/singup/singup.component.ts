import { Component, OnInit,ViewChild} from '@angular/core';
import { NgForm } from '@angular/forms';
import { SecurityService } from 'src/app/services/security/security.service';
import {userregister} from  'src/app/components/userregister'

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.scss']
})
export class SingupComponent implements OnInit {
  @ViewChild('signupForm', { static: false })
  signupForm: NgForm;

  constructor(private securityService: SecurityService) { }

  ngOnInit() {
  }
  user:userregister={
    username:'',
    password:''
  };

  register() {
    console.log('testing'+this.signupForm.value.username)
    this.user.username=this.signupForm.value.username;
    this.user.password=this.signupForm.value.password;

    this.securityService.registration(this.user);

  }

}
