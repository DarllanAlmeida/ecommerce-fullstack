import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';


@Component({

  selector:'app-order-success',

  standalone:true,

  imports:[

    CommonModule,

    RouterModule

  ],

  templateUrl:'./order-success.component.html',

  styleUrl:'./order-success.component.css'

})
export class OrderSuccessComponent implements OnInit {



  order:any;





  ngOnInit():void {



    this.order = history.state.order;




    console.log(

      "ORDER RECIBIDO:",

      this.order

    );



  }







}