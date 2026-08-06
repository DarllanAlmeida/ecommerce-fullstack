import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { CartService, CartItem } from '../services/cart.service';
import { OrderService } from '../services/order.service';



@Component({

  selector: 'app-cart',

  standalone: true,

  imports: [
    CommonModule
  ],

  templateUrl: './cart.component.html',

  styleUrl: './cart.component.css'

})


export class CartComponent implements OnInit {



  items: CartItem[] = [];


  total:number = 0;







  constructor(

    public cartService: CartService,

    private orderService: OrderService,

    private router: Router

  ) {}









  ngOnInit():void {


    this.loadCart();


  }









  loadCart():void {


    this.items = this.cartService.getItems();


    this.total = this.cartService.getTotal();


  }









  // Aumentar cantidad por variante


  increase(

    productVariantId:number

  ):void {


    this.cartService.increaseQuantity(

      productVariantId

    );


    this.loadCart();


  }









  // Disminuir cantidad por variante


  decrease(

    productVariantId:number

  ):void {


    this.cartService.decreaseQuantity(

      productVariantId

    );


    this.loadCart();


  }









  // Eliminar variante


  confirmRemove(

    productVariantId:number

  ):void {



    const confirmDelete = confirm(

      '¿Seguro que quieres eliminar este producto del carrito?'

    );





    if(confirmDelete){



      this.cartService.removeFromCart(

        productVariantId

      );



      this.loadCart();



    }



  }









  // Vaciar carrito


  clearCart():void {



    const confirmClear = confirm(

      '¿Seguro que quieres vaciar todo el carrito?'

    );





    if(confirmClear){



      this.cartService.clearCart();



      this.loadCart();



    }



  }









  // Crear pedido


  checkout():void {



    if(

      this.items.length === 0

    ){



      console.warn(

        'Carrito vacío'

      );


      return;



    }







    /*
      TEMPORAL

      Después lo sacaremos del usuario logueado

      y de sus direcciones.
    */


    const customerId = 1;


    const addressId = 1;








    this.orderService

    .createOrder(

      customerId,

      addressId

    )

    .subscribe({





      next:(order)=>{



        console.log(

          'PEDIDO CREADO',

          order

        );





        this.cartService.clearCart();



        this.loadCart();







        this.router.navigate(

          ['/order-success'],

          {

            state:{

              order

            }

          }

        );





      },







      error:(error)=>{



        console.error(

          'ERROR CREANDO PEDIDO',

          error

        );



      }





    });



  }





}
