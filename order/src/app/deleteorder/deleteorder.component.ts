import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-deleteorder',
  templateUrl: './deleteorder.component.html',
  styleUrls: ['./deleteorder.component.css']
})
export class DeleteorderComponent implements OnInit {
  constructor(private userService: UserService) {

  }

  ngOnInit() {
    this.getallordersbyDealerId();
  }
  getallordersbyDealerId() {
    this.userService.getallordersbyDealerId().subscribe(
      success => {
        // console.log(success)
        this.getallordersbyDealerId = success['content'];
        //this.pages = new Array(success['totalPages'])
      },
      error => { console.log(error.error.message) }
    );
  }
  deleteOrder(planId: number) {
    // console.log(planId);
    this.userService.deleteOrder(planId).subscribe(
      success => { console.log(success); this.getallordersbyDealerId(); },
      error => { console.log(error) }
    );
  }

}
