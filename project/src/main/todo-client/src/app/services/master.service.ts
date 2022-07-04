import { HttpClient, HttpParams,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SecurityService } from './security/security.service';

@Injectable({
  providedIn: 'root'
})
export class MasterService {




  deleteFromList(taskId: any) {
    const httpOptions2 ={
      headers:new HttpHeaders({
        'Content-type':'application/json',
        'Authorization' : 'Bearer '+this.Security.jwtToken,
      })
    }
    return this.http.delete('/deleteTodoList/'+taskId,httpOptions2);
  }

  constructor(private http: HttpClient,private Security:SecurityService) { }

  /**
   * Get ToDo list for loggedin user
   * @returns Observable<any>
   */


  getToDoList(): Observable<any> {
    const httpOptions1 ={
      headers:new HttpHeaders({
        'Content-type':'application/json',
        'Authorization' : 'Bearer '+this.Security.jwtToken,
      })
    }
    return this.http.get('/getTodoList/'+this.Security.userId,httpOptions1);
  }

  /**
   * Save ToDo list against loggedin user
   * @param data 
   * @returns Observable<any>
   */
  saveToDoList(data: Array<any>): Observable<any> {
    // return this.http.post('/saveTodoList', data);
    const httpOptions ={
      headers:new HttpHeaders({
        'Content-type':'application/json',
        'Authorization' : 'Bearer '+this.Security.jwtToken,
      })
    }
    console.log(httpOptions)
    return this.http.post('/saveTodoList',data ,httpOptions)
  };


  getCustomHeaders(): HttpHeaders {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', 'Bearer '+this.Security.jwtToken);
    return headers;
  }



  }

// }
