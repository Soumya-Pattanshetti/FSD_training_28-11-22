import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent  {
  constructor(public loginService: LoginService, private cookieService: CookieService) {

  }

  flushCookies(): void {
    this.cookieService.deleteAll();
    this.loginService.user = null;
    sessionStorage.removeItem('token');
  }
}