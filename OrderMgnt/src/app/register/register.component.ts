import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterUser } from '../entity/User';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  register = new RegisterUser();
 
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  

  constructor(private authService: LoginService,private router:Router) { }

  ngOnInit(): void {
  }
  onSubmit(f:NgForm): void {
    
    console.log(this.register.Roles)
    console.log(this.register.username);
    this.authService.register(this.register).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        f.resetForm();
        alert(" Registered Successfully!");
        this.router.navigate(['/login']);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
   
  
  }
}