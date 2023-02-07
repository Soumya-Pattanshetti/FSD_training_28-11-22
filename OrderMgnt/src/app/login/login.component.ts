import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { TokenStorageService } from '../service/toker-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {
    username: null,
    password: null
  };
  isAdmin=false;
  isUser=true;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = ["USER","ADMIN"];
  useRole:string="";
  constructor(private authService: LoginService, private tokenStorage: TokenStorageService,private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;
   console.log(username);
   console.log(password);
    this.authService.readerLogin(username, password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        console.log(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        
        this.roles = this.tokenStorage.getUser().roles;
        
        this.reloadPage();
        this.isAdmin = this.roles.includes('ROLE_ADMIN');
        this.isUser = this.roles.includes('ROLE_USER');
        if(this.isAdmin){
          this.reloadPage();
        this.router.navigate(['admindashboard']);
        }
        if(this.isUser){
          this.router.navigate(['Userdashboard']);
        }
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

  info(){
    alert("Please login as Guest user to register!");
  }

}

