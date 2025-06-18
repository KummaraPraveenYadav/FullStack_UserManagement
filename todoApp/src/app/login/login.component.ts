import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthenticationServiceService } from '../service/authentication-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {
userName:String|null='';
  isLoginSuccess:boolean=true;
  email="";
  password="";
  //handleBEAuthentication: any;
   
  constructor(private route:Router ,private authService:AuthenticationServiceService){}

  handleLogin(){
    //backend service
    const postData = {
     email: this.email,
     password: this.password
    };
     this.authService.handleBEAuthentication(postData).subscribe({
      next: (successResponse) =>{
        this.isLoginSuccess = true;
        console.log(successResponse.headers.get('username'));

        //Its a success, so we will naviagte to list-todo page
        //backend call here to get the firstName with the help of email
        this.userName=successResponse.headers.get('username');
        const userId=successResponse.headers.get('userid');
        let firstName = 'praveen'
        this.route.navigate(['/welcome', this.userName], {queryParams:{id:userId}});
      },
      error: (errorValue) =>{
        console.log(errorValue);
        this.isLoginSuccess = !this.isLoginSuccess;
      }
    });
  }

}
