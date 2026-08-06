import { Injectable } from '@angular/core';



export interface CartItem {


  productId: number;


  productVariantId: number;


  name: string;


  size: string;


  color: string;


  price: number;


  quantity: number;


  stock: number;


  imageUrl: string;


}






@Injectable({
  providedIn: 'root'
})
export class CartService {



  private items: CartItem[] = [];





  constructor() {


    this.loadCart();


  }









  // Cargar carrito

  private loadCart(): void {


    const savedCart = localStorage.getItem('cart');



    if(savedCart){


      this.items = JSON.parse(savedCart);


    }


  }









  // Guardar carrito

  private saveCart(): void {


    localStorage.setItem(

      'cart',

      JSON.stringify(this.items)

    );


  }









  // Obtener items

  getItems(): CartItem[] {


    return this.items;


  }









  // Añadir producto

  // variantId es opcional para no romper componentes antiguos

  addToCart(

    product:any,

    variantId?:number

  ):void {





    // Si no viene variante usamos la primera disponible

    if(!variantId){


      if(

        product.variants &&

        product.variants.length > 0

      ){


        variantId = product.variants[0].id;


      }else{


        return;


      }


    }







    const variant = product.variants.find(

      (v:any) =>

      v.id === variantId

    );





    if(!variant){


      return;


    }







    const existing = this.items.find(

      item =>

      item.productVariantId === variantId

    );








    if(existing){



      if(existing.quantity < existing.stock){


        existing.quantity++;


      }



    }else{



      this.items.push({



        productId: product.id,



        productVariantId: variant.id,



        name: product.name,



        size: variant.size,



        color: variant.color,



        price: variant.price,



        quantity:1,



        stock:variant.stock,



        imageUrl: product.imageUrl || ''



      });



    }







    this.saveCart();



  }









  // Aumentar cantidad

  increaseQuantity(

    variantId:number

  ):void {



    const item = this.items.find(

      i =>

      i.productVariantId === variantId

    );





    if(

      item &&

      item.quantity < item.stock

    ){


      item.quantity++;


      this.saveCart();


    }


  }









  // Disminuir cantidad

  decreaseQuantity(

    variantId:number

  ):void {



    const item = this.items.find(

      i =>

      i.productVariantId === variantId

    );





    if(

      item &&

      item.quantity > 1

    ){


      item.quantity--;


      this.saveCart();


    }


  }









  // Eliminar producto

  removeFromCart(

    variantId:number

  ):void {



    this.items = this.items.filter(

      item =>

      item.productVariantId !== variantId

    );



    this.saveCart();



  }









  // Vaciar carrito

  clearCart():void {



    this.items = [];



    this.saveCart();



  }









  // Total carrito

  getTotal():number {



    return this.items.reduce(

      (total,item) =>

      total +

      item.price *

      item.quantity,

      0

    );


  }









  // Contador

  getCartCount():number {



    return this.items.reduce(

      (total,item) =>

      total +

      item.quantity,

      0

    );


  }



}
