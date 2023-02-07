import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../entity/User';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private API_URL = "http://localhost:8085/order/";

  constructor(private loginService: LoginService, private httpClient: HttpClient) { }
  getAllOrders(): Observable<any> {
    let userdata_URL =this.API_URL + "getAllOrders";
    return this.httpClient.get<User[]>(userdata_URL, { headers: this.httpHeaderWithJwtToken() });
  }
  httpHeaderWithJwtToken(): HttpHeaders {
    let token = this.loginService.user.token;//sessionStorage.getItem('token');
    const headers = new HttpHeaders()
      .set("Content-Type", "application/json")
      .set("Authorization", "Bearer " + token)
    return headers;
  }
}
