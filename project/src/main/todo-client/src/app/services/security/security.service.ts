import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { userdetails } from 'src/app/components/userdetails';
import { userregister } from 'src/app/components/userregister';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private authToken: string = null;
  isLoggedIn: boolean = false;
  loggedinUser: any;

  constructor(
    private http: HttpClient, 
    private toastrService: ToastrService, 
    private router: Router
  ) { }

  /**
   * Validate user and fetch user context
   */
  validateUser() {
    this.http.get('/getLoggedinUser').subscribe((res) => {
      this.isLoggedIn = true;
      this.loggedinUser = res;
      this.router.navigate(['/dashboard']);
    }, () => {
      this.router.navigate(['/home']);
    });
  }

  /**
   * Logout user from application and reset necessary variables
   */
  logout(): void {
    this.http.get('/logout').subscribe((res) => {
      this.isLoggedIn = false;
      this.authToken = null;
      this.router.navigate(['home']);
    }, err => this.toastrService.error(err.message ? err.message : err));
  }

  /**
   * Helper method to create authentication token
   * @param username
   * @param password 
   */
  jwtToken:any;
  userId:number;
  updateCredentials(user:userdetails) {
    // this.authToken = btoa(username + ':' + password);
    this.http.post('/authenticate',user,{}).subscribe((res) => {
      console.log('print ressss'+res)
      this.jwtToken=res.jwt;
      this.userId=res.userID;
      console.log('layer 1 '+this.jwtToken)
      console.log('layer 2'+this.userId)
      this.isLoggedIn=true;
      this.router.navigate(['/dashboard']);
      
    }, () => {
      this.router.navigate(['/home']);
    });

  }

 registration(user:userregister) {
  this.http.post('/register',user,{}).subscribe((res) => {
    console.log('print ressss'+res)

    this.router.navigate(['/home']);
    this.toastrService.success('Registration Success');
  }, () => {
    this.router.navigate(['/signup']);
  });
 }


  /**
   * Get authentication token
   * 
   * @returns string
   */
  getAuthToken(): string {
    return this.authToken;
  }

  /**
   * Reset authentication token
   */
  resetAuthToken(): void {
    this.authToken = null;
  }

}
