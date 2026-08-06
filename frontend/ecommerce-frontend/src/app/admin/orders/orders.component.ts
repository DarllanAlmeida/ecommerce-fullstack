import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';


import {
  OrderService,
  Order
} from '../../services/order.service';







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



  orders:Order[] = [];


  loading:boolean = true;


  selectedOrder:Order | null = null;






  statuses:string[] = [

    'PENDING',

    'PAID',

    'SHIPPED',

    'DELIVERED',

    'CANCELLED'

  ];








  constructor(

    private orderService:OrderService

  ) {}









  ngOnInit():void {


    this.loadOrders();


  }









  loadOrders():void {



    this.orderService

    .getOrders()

    .subscribe({





      next:(data:Order[])=>{


        this.orders = data;


        this.loading = false;



      },







      error:(error)=>{


        console.error(

          'Error cargando pedidos',

          error

        );


        this.loading = false;



      }





    });



  }









  openDetail(order:Order):void {


    this.selectedOrder = order;


  }









  closeDetail():void {


    this.selectedOrder = null;


  }









  formatDate(date:string):string {



    return new Date(date)

      .toLocaleDateString(

        'es-ES'

      );


  }









  // ==========================
  // TRADUCIR ESTADOS
  // ==========================


  getStatusLabel(status:string):string {



    switch(status) {



      case 'PENDING':

        return 'Pendiente';





      case 'PAID':

        return 'Pagado';





      case 'SHIPPED':

        return 'Enviado';





      case 'DELIVERED':

        return 'Entregado';





      case 'CANCELLED':

        return 'Cancelado';





      default:

        return status;



    }


  }









  // ==========================
  // CAMBIAR ESTADO
  // ==========================


  changeStatus(

    order:Order,

    event:any

  ):void {



    const status = event.target.value;






    this.orderService

    .updateStatus(

      order.id,

      status

    )

    .subscribe({





      next:(updated:Order)=>{



        order.status = updated.status;



        alert(

          'Estado actualizado correctamente'

        );



      },






      error:(error)=>{



        console.error(

          'Error actualizando estado',

          error

        );



        alert(

          'Error actualizando estado'

        );



      }





    });



  }







}
