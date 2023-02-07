import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NGXLogger } from 'ngx-logger';
import { User } from '../entity/User';
import { LoginService } from '../service/login.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  createOrderForm!: FormGroup;
  errorMessage:string="";
  user?:User;
  previousBooksBySameUser!: Array<any>;
  constructor(private userservice:UserService,
    public loginService:LoginService,
    private formBuilder:FormBuilder,
    private logger:NGXLogger){
      this.user = loginService.user;
    }

  ngOnInit(){
    this.createOrderForm = this.formBuilder.group({
      carname:String,
      modelname:String,
      sysCode:Number,
      indicator:String,
      date:Date,

    });
    this.createOrderForm.get('order')?.disable();
    //this.getAllOrdersByUser();
  }

  createOrder(){
    this.userservice.addOrder(this.createOrderForm.value).subscribe(
      success=>{
        // console.log(success);
        this.getAllOrdersByUser();
      },
      error =>{this.logger.warn(error)}
    );
  }
  getAllOrdersByUser(){
    this.userservice.getallordersbyDealerId().subscribe(
      success =>{
        this.previousBooksBySameUser = success
      },
      error => {this.logger.warn("Error Occured While Retrieving the books:" + error)}
    );
  }
}
