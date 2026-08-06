import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

import {
  OrderService,
  Order,
  OrderHistory
} from '../services/order.service';



@Component({

  selector:'app-order-detail',

  standalone:true,

  imports:[

    CommonModule

  ],

  templateUrl:'./order-detail.component.html',

  styleUrl:'./order-detail.component.css'

})


export class OrderDetailComponent implements OnInit {



  order!: Order;


  history: OrderHistory[] = [];



  loading = true;





  /*
    Estados internos del backend
    con traducción en frontend
  */


  steps = [

    {
      key:'PENDING',
      label:'Pedido recibido'
    },

    {
      key:'PAID',
      label:'Pago confirmado'
    },

    {
      key:'SHIPPED',
      label:'Enviado'
    },

    {
      key:'DELIVERED',
      label:'Entregado'
    }

  ];









  constructor(

    private route: ActivatedRoute,

    private orderService: OrderService

  ) {}









  ngOnInit(): void {



    const id = Number(

      this.route.snapshot.paramMap.get('id')

    );



    this.loadOrder(id);


    this.loadHistory(id);



  }









  loadOrder(id:number):void {



    this.orderService

    .getOrderById(id)

    .subscribe({



      next:(data)=>{


        this.order = data;


        this.loading = false;



      },



      error:(err)=>{


        console.error(

          'ERROR CARGANDO PEDIDO',

          err

        );


        this.loading = false;


      }



    });



  }









  loadHistory(id:number):void {



    this.orderService

    .getHistory(id)

    .subscribe({



      next:(data)=>{


        this.history = data;



      },



      error:(err)=>{


        console.error(

          'ERROR CARGANDO HISTORIAL',

          err

        );


      }



    });



  }









  /*
    Comprueba si está cancelado
  */


  isCancelled():boolean {



    return this.order?.status === 'CANCELLED';



  }









  /*
    Marca pasos completados
  */


  isCompleted(step:string):boolean {



    if(!this.order){


      return false;


    }







    if(this.order.status === 'CANCELLED'){


      return false;


    }







    const currentIndex =

    this.steps.findIndex(

      s => s.key === this.order.status

    );







    const stepIndex =

    this.steps.findIndex(

      s => s.key === step

    );







    return stepIndex <= currentIndex;



  }









  /*
    Traducción estado pedido
  */


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









  /*
    Cancelar pedido
  */


  cancelOrder():void {



    if(!this.order){


      return;


    }







    this.orderService

    .cancelOrder(this.order.id)

    .subscribe({



      next:(updated)=>{


        this.order = updated;



      },



      error:(err)=>{


        console.error(

          'ERROR CANCELANDO PEDIDO',

          err

        );


      }



    });



  }






}
