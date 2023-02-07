import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  errorMessage!:string;
  

  constructor(
    private userService:LoginService,
    private formBuilder:FormBuilder,
    private router: Router,
    private cookieService: CookieService){}
  
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      emailId:['',[Validators.required,Validators.email,Validators.min(3)]],
      password:['',[Validators.required,Validators.min(1)]],
    });
  }

  userLogin(){
    console.log(this.loginForm.value);
    this.userService.loginUser(this.loginForm.value).subscribe(
      success=>{ 
        console.log(success)
        this.userService.user = success;
        sessionStorage.setItem('token',this.userService.user.token)
        this.cookieService.set("userData",JSON.stringify(success));
        this.router.navigate([''])
      },
      error=>{
        // this.logger.warn(error);
        console.log(error)
        this.errorMessage = error.error.errorMessage;
      }
      );
  }

}
