import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerationForm !: FormGroup;
  errorMessage : string = "";

  constructor(
    private userService: LoginService,
    private formBuilder: FormBuilder,private router: Router) { }

  ngOnInit(): void {
    this.registerationForm = this.formBuilder.group({
      userName:['',[Validators.required,Validators.pattern('^[A-Za-z ]+$'),Validators.minLength(1)]],
      emailId:['',[Validators.required,Validators.email,Validators.minLength(3),Validators.maxLength(50)]],
      password:['',[Validators.required,Validators.minLength(5),Validators.maxLength(50)]],
      role:['USER',[Validators.required]],
    });
  }

  registerUser() { 
    console.log(this.registerationForm.value);
    this.userService.registerUser(this.registerationForm.value);
    this.router.navigate([''])
  }
}

