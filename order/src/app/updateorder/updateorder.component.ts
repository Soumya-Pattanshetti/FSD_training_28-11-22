import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { LoginService } from '../service/login.service';
import { UserService } from '../service/user.service';
import { NGXLogger } from 'ngx-logger';

@Component({
  selector: 'app-updateorder',
  templateUrl: './updateorder.component.html',
  styleUrls: ['./updateorder.component.css']
})
export class UpdateorderComponent  {
  updateOrderForm!: FormGroup;
  errorMessage: string = "";
  previousBooksBySameUser!: Array<any>;

  constructor(private userService: UserService,
    public loginService: LoginService,
    private formBuilder: FormBuilder,
    private logger: NGXLogger) { }

    ngOnInit() {
      this.updateOrderForm = this.formBuilder.group({
        carname:String,
      modelname:String,
      sysCode:Number,
      indicator:String,
      date:Date,
      });
      this.updateOrderForm.get('author')?.disable();
      this.getAllOrdersByUser();
    }
    getAllOrdersByUser(){
      this.userService.getallordersbyDealerId().subscribe(
        success =>{
          this.previousBooksBySameUser = success
        },
        error => {this.logger.warn("Error Occured While Retrieving the books:" + error)}
      );
    }

    updateOrder() {
      this.userService.updateOrder(this.updateOrderForm.value).subscribe(
        success => this.getAllOrdersByUser(),
        error => this.logger.warn(error)
      );
    }
  
}
