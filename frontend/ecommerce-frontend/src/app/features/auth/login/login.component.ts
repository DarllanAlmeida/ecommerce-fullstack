import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../../../services/auth.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {


  email = '';

  password = '';

  errorMessage = '';



  constructor(
    private authService: AuthService,
    private router: Router
  ) {}





  login(): void {


    this.errorMessage = '';



    this.authService
      .login(
        this.email,
        this.password
      )
      .subscribe({



        next: (response) => {



          console.log(
            'LOGIN RESPONSE:',
            response
          );



          this.authService.saveSession(
            response
          );



          this.router.navigate(['/']);



        },



        error: () => {



          this.errorMessage =
            'Email o contraseña incorrectos';



        }



      });



  }



}
