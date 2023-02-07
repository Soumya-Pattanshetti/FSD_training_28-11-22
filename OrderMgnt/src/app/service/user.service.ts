import { HttpClient, HttpHeaders } from '@angular/common/http';
import { identifierModuleUrl } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { OrderDemo } from '../entity/OrderDemo';

const API_URL = environment.api;
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class userService {

  constructor(private http: HttpClient) { }

  createOrder(OrderDemo:any,dealerId:number):Observable<string>{
    return this.http.post<string>(API_URL+"createOrder/"+dealerId,OrderDemo,httpOptions);
  }

  updateBook(orderdemo:OrderDemo,OrderId:number):Observable<string>{
    return this.http.put<string>(API_URL+"admin/updateBook/"+OrderId,orderdemo,httpOptions);
  }




  getOrderListByAuthorId(dealerId:number){
    return this.http.get(API_URL+"getbooklistbyauthorid/"+dealerId);
  }

  
}
