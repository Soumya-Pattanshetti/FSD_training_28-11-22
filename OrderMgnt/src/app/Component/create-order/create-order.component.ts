import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {
  book = new Book();
  constructor(private authorService:AuthorService,private tokenStorage: TokenStorageService) { }
  bookLogo:string="https://th.bing.com/th/id/OIP.y-Llu8GN_V3J7e1YYGST0AHaHa?w=184&h=184&c=7&r=0&o=5&pid=1.7";
  authorId:number;
  Created:string;
bookName:string;
authorName:string;
  onSubmit(f:NgForm){
    console.log(this.book);
    this.bookLogo = String(this.book.bookLogo);
    console.log(this.tokenStorage.getUser().id);
    this.authorService.createBook(this.book,Number(sessionStorage.getItem("authorId")))
    .subscribe(data => {
      this.bookName = data;
      alert("Book "+this.bookName+" Created Successfully!");
    })
    f.resetForm();
    this.Created = "Created!";
  }
  // imgCheck(){
  //   this.bookLogo = String(this.book.bookLogo);
  // }
  ngOnInit(): void {
  }


}
