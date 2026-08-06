import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

import { ProductService } from '../../services/product.service';
import { CartService } from '../../services/cart.service';
import { FavoritesService } from '../../services/favorites.service';

import { Product, ProductVariant } from '../../models/product';



@Component({

  selector:'app-product-detail',

  standalone:true,

  imports:[
    CommonModule
  ],

  templateUrl:'./product-detail.component.html',

  styleUrl:'./product-detail.component.css'

})


export class ProductDetailComponent implements OnInit {



  product?: Product;


  loading = true;


  quantity = 1;



  selectedVariantId?: number;



  cartMessage = '';



  isFavorite = false;







  constructor(

    private route: ActivatedRoute,

    private productService: ProductService,

    private cartService: CartService,

    private favoritesService: FavoritesService

  ) {}









  ngOnInit():void {



    const id = Number(

      this.route.snapshot.paramMap.get('id')

    );





    this.productService

    .getProductById(id)

    .subscribe({



      next:(product)=>{



        this.product = product;



        this.loading = false;



        this.checkFavorite();




        // Seleccionar primera variante automáticamente

        if(

          product.variants &&

          product.variants.length > 0

        ){


          this.selectedVariantId =

          product.variants[0].id;


        }




      },



      error:(error)=>{


        console.error(error);


        this.loading = false;


      }



    });



  }









  // Seleccionar talla / variante


  selectVariant(

    variant: ProductVariant

  ):void {



    this.selectedVariantId = variant.id;


  }









  // Stock de la variante seleccionada


  getSelectedStock():number {



    if(

      !this.product ||

      !this.selectedVariantId

    ){


      return 0;


    }






    const variant =

    this.product.variants.find(


      v =>

      v.id === this.selectedVariantId


    );






    return variant ? variant.stock : 0;



  }









  increase():void {



    if(

      this.quantity < this.getSelectedStock()

    ){


      this.quantity++;


    }



  }









  decrease():void {



    if(this.quantity > 1){


      this.quantity--;


    }



  }









  addToCart():void {



    if(!this.product){


      return;


    }






    if(!this.selectedVariantId){



      this.cartMessage =

      'Selecciona una talla';



      return;


    }








    this.cartService.addToCart(

      this.product,

      this.selectedVariantId

    );







    this.cartMessage =

    'Producto añadido al carrito';








    setTimeout(()=>{


      this.cartMessage = '';



    },2500);



  }









  toggleFavorite():void {



    if(!this.product){


      return;


    }






    this.favoritesService

    .toggleFavorite(

      this.product

    );







    this.isFavorite =

    this.favoritesService

    .isFavorite(

      this.product.id

    );



  }









  checkFavorite():void {



    if(!this.product){


      return;


    }






    this.isFavorite =

    this.favoritesService

    .isFavorite(

      this.product.id

    );



  }



}
