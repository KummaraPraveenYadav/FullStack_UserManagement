import { inject, Injectable } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthenticationServiceService } from '../authentication-service.service';

export const authGuard: CanActivateFn=(route, state) =>{
  const authService = inject(AuthenticationServiceService);
  const router = inject(Router);
  if(!authService.isUserLoggedIn()){
    return router.createUrlTree(["/login"]);
  }else{
    return true;
  }
}