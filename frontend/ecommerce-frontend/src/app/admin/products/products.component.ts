import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';

import { RouterLink } from '@angular/router';


import { ProductService } from '../../services/product.service';


import { Product } from '../../models/product';







@Component({

  selector:'app-products',

  standalone:true,

  imports:[

    CommonModule,

    RouterLink

  ],

  templateUrl:'./products.component.html',

  styleUrl:'./products.component.css'

})


export class ProductsComponent implements OnInit {



  products:Product[] = [];


  loading:boolean = true;







  constructor(

    private productService:ProductService

  ) {}









  ngOnInit():void {


    this.loadProducts();


  }









  loadProducts():void {



    this.loading = true;





    this.productService

    .getProducts()

    .subscribe({





      next:(data:Product[])=>{



        this.products = data;



        this.loading = false;



      },







      error:(error)=>{



        console.error(

          'Error cargando productos',

          error

        );



        this.loading = false;



      }






    });



  }









  deleteProduct(id:number):void {



    const confirmDelete = confirm(

      '¿Seguro que quieres eliminar este producto?'

    );





    if(!confirmDelete){

      return;

    }









    this.productService

    .deleteProduct(id)

    .subscribe({





      next:()=>{



        alert(

          'Producto eliminado correctamente'

        );



        this.loadProducts();



      },







      error:(error)=>{



        console.error(

          'Error eliminando producto',

          error

        );



        alert(

          'No se pudo eliminar el producto'

        );



      }







    });



  }









  getVariantCount(product:Product):number {



    return product.variants

      ? product.variants.length

      : 0;



  }






}
