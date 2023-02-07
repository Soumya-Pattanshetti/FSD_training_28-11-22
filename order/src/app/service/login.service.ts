import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { User } from '../entity/User';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public user: any;
  private authenticationURL = "http://8083/";
  private loginUrl = this.authenticationURL + "authenticate";
  private registerUrl = this.authenticationURL + "register";

  constructor(
    private httpClient: HttpClient, private cookieService: CookieService) { }

  loginUser(user: { username: string, password: string }): Observable<any> {
    return this.httpClient.post<User>(this.loginUrl, user);
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