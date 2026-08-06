import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';





export interface LoginResponse {

  token:string;

  email?:string;

  role?:string;

}







@Injectable({
  providedIn:'root'
})
export class AuthService {



  private apiUrl =

    'http://localhost:8080/api/auth';







  constructor(

    private http:HttpClient

  ) {}









  login(

    email:string,

    password:string

  ):Observable<LoginResponse> {


    return this.http.post<LoginResponse>(

      `${this.apiUrl}/login`,

      {

        email,

        password

      }

    );


  }









  register(

    user:any

  ):Observable<any> {


    return this.http.post(

      `${this.apiUrl}/register`,

      user

    );


  }









  saveToken(

    token:string

  ):void {


    localStorage.setItem(

      'token',

      token

    );


  }









  saveSession(

    response:LoginResponse

  ):void {


    localStorage.setItem(

      'token',

      response.token

    );


  }









  getToken():string | null {


    return localStorage.getItem(

      'token'

    );


  }









  isLoggedIn():boolean {


    return this.getToken() !== null;


  }









  logout():void {


    localStorage.removeItem(

      'token'

    );


  }







}