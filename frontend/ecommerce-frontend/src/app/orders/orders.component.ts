import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { OrderService, Order } from '../services/order.service';



@Component({

  selector:'app-orders',

  standalone:true,

  imports:[

    CommonModule

  ],

  templateUrl:'./orders.component.html',

  styleUrl:'./orders.component.css'

})


export class OrdersComponent implements OnInit {



  orders: Order[] = [];







  constructor(

    private orderService:OrderService,

    private router:Router

  ){}





  ngOnInit():void{


    this.loadOrders();


  }









  loadOrders():void {



    this.orderService

    .getOrders()

    .subscribe({



      next:(data)=>{


        this.orders = data;



        console.log(

          'PEDIDOS:',

          this.orders

        );


      },



      error:(err)=>{


        console.error(

          'ERROR PEDIDOS:',

          err

        );


      }



    });



  }









  viewOrder(id:number):void {



    this.router.navigate([

      '/orders',

      id

    ]);



  }









  translateStatus(status:string):string {



    const states:any = {


      PENDING:'Pendiente',


      PAID:'Pagado',


      SHIPPED:'Enviado',


      DELIVERED:'Entregado',


      CANCELLED:'Cancelado'


    };





    return states[status] || status;



  }









  getStatusClass(status:string):string {



    const classes:any = {



      PENDING:'pending',


      PAID:'paid',


      SHIPPED:'shipped',


      DELIVERED:'delivered',


      CANCELLED:'cancelled'


    };





    return classes[status] || 'pending';



  }





}