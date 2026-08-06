import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';


@Component({

  selector:'app-admin-layout',

  standalone:true,

  imports:[
    CommonModule,
    RouterOutlet,
    RouterLink
  ],

  templateUrl:'./admin-layout.component.html',

  styleUrl:'./admin-layout.component.css'

})


export class AdminLayoutComponent {


}
