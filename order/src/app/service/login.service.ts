import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { User } from '../entity/User';

const httpOptions={
  headers: new HttpHeaders({'Content-Type':'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  public user: any;
  private authenticationURL = "http://localhost:8083/";
  private loginUrl = this.authenticationURL + "authenticate";
  private registerUrl = this.authenticationURL + "register";
 
  constructor(
    private httpClient: HttpClient, private cookieService: CookieService) { }

  loginUser(user: { emailId: string, password: string ,content:any}): Observable<any> {
    //this.cookieService.set("userData","39418F7C70E4019E8CED574492D6A26C");
    return this.httpClient.post<User>(this.loginUrl, user,httpOptions);
    
  }

  registerUser(user: User) {
    this.httpClient.post<User>(this.registerUrl, user).subscribe(
      success => {
        this.user = success;
        sessionStorage.setItem('token', user.token);
        this.cookieService.set("userData", JSON.stringify(success));
      }
    );
  }
}