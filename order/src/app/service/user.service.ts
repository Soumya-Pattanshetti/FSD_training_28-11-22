import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { LoginService } from './login.service';
import { orderDemo } from '../entity/orderDemo';
import { from, Observable } from 'rxjs';
import { User } from '../entity/User';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  //private AWS_URL = "3.110.23.139";
  private API_URL = "http://localhost:8085/order/";

  constructor(
    private httpClient: HttpClient,
    public loginUserService: LoginService) {

  }
 httpHeaderWithJwtToken(): HttpHeaders {
    let token = this.loginUserService.user.token;//sessionStorage.getItem('token');
    const headers = new HttpHeaders()
      .set("Content-Type", "application/json")
      .set("Authorization", "Bearer " + token)
    return headers;
  }
  addOrder(order: orderDemo): Observable<any> {
    let addorderUrl: string = this.API_URL  + "createOrder"+ parseInt(this.loginUserService.user!.id);
    return this.httpClient.post(addorderUrl, order, { headers: this.httpHeaderWithJwtToken() });
}

getallordersbyDealerId(): Observable<any> {
  return this.httpClient.get(this.API_URL + "allordersbyDealerId"+this.loginUserService.user!.id, { headers: this.httpHeaderWithJwtToken() });
}
updateOrder(order: orderDemo): Observable<any> {
  let updateOrderUrl: string = this.API_URL +"updateOrder"+ parseInt(this.loginUserService.user!.id);
  return this.httpClient.put(updateOrderUrl, order, { headers: this.httpHeaderWithJwtToken() });
}
deleteOrder(orderId: number): Observable<any> {
    
  let userdata_URL = this.API_URL + this.loginUserService.user!.id + orderId;
  return this.httpClient.get<User[]>(userdata_URL, { headers: this.httpHeaderWithJwtToken() });
}
}
