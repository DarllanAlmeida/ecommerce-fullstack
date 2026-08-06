import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';


@Component({

selector:'app-dashboard',

standalone:true,

imports:[
 CommonModule
],

templateUrl:'./dashboard.component.html',

styleUrl:'./dashboard.component.css'

})


export class DashboardComponent {


products = 0;

orders = 0;

users = 0;

sales = 0;



}