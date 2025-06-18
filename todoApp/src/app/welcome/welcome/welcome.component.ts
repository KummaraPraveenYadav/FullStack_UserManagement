import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})

export class WelcomeComponent implements OnInit {
  nameFromUrl: string="";
 userId: string = '';

  constructor(private activedRoute: ActivatedRoute){}
  ngOnInit(): void {
    
  //  this.nameFromUrl= this.activedRoute.snapshot.params['name'];
  //  this.userId=this.activedRoute.snapshot.queryParams["userId"];
   this.activedRoute.params.subscribe(parameter=>{
    this.nameFromUrl = parameter['name'];
    console.log(this.nameFromUrl)
    sessionStorage.setItem('name',this.nameFromUrl);

   });
   this.activedRoute.queryParams.subscribe(parameter=>{
    this.userId = parameter['id'];
 });
  }

  
}
