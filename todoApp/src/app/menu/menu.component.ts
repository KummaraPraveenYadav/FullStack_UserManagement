import { Component } from '@angular/core';
import { AuthenticationServiceService } from '../service/authentication-service.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  name=sessionStorage.getItem('name');
   constructor( public authService: AuthenticationServiceService){}

}
