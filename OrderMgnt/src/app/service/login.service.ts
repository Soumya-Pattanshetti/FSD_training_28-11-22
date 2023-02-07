import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterUser } from '../entity/User';

const api = "http://ec2-52-193-213-51.ap-northeast-1.compute.amazonaws.com:8082/api/auth";
//const api="http://localhost:8082/api/auth"
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  requestHeader = new HttpHeaders({  });
  constructor(private httpClient:HttpClient) { }

  readerLogin(username: string, password: string):Observable<any>{
    return this.httpClient.post<any>(api+"/authenticate",{
      username,
      password
    }
    , httpOptions);
  }
  register(register:RegisterUser): Observable<any> {
    return this.httpClient.post(api + '/register', register, httpOptions);
  }
}
