import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

declare const appUrl: String

@Injectable({
  providedIn: 'root'
})
export class AuthenticationServiceService {
  constructor(private http : HttpClient){}

  handleBEAuthentication(postData:any){
    return (this.http.post(appUrl+'login', postData, {headers : {'Content-Type': 'application/json'}, observe: 'response'}).pipe(
      map(data=>{
         const token = data.headers.get('Authorization');
         if(token){
          sessionStorage.setItem("Auth_token", token);
         }
         return data;
      })
    ));
  }

  register(value: any):Observable<any>{
        return this.http.post(appUrl+"users/createUser",value,{headers:{'Content-type':'application/json'},observe:'response'})
  }

  isUserLoggedIn(){
    return sessionStorage.getItem("Auth_token");
  }
  isAuthenticationTokenAvailable(): string| null{
    if(this.isUserLoggedIn()){
      return sessionStorage.getItem("Auth_token");
    }
    return null;
  }
  logoutUser(){
    sessionStorage.removeItem("Auth_token");
    sessionStorage.removeItem("user_id");
  }
}