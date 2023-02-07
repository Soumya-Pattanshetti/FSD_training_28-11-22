import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { TokenStorageService } from 'src/app/service/toker-storage.service';

@Component({
  selector: 'app-home-navbar',
  templateUrl: './home-navbar.component.html',
  styleUrls: ['./home-navbar.component.css']
})
export class HomeNavbarComponent implements OnInit {

  isGuest:boolean=false;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  constructor(private authService: LoginService, private tokenStorage: TokenStorageService,private router: Router) { }
  roles: string[] = [];
  ngOnInit(): void {
  }
  username:string="admin";
  password:string="password";
  onLogin(){
    console.log("here");
    
    this.authService.readerLogin(this.username,this.password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        console.log(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles
        this.reloadPage();
        this.isGuest = this.roles.includes('ROLE_ADMIN');
        if(this.isGuest){
          this.reloadPage();
          this.router.navigate(['admindashboard']);
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
  
}
