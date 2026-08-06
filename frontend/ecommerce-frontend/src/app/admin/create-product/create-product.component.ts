import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';


import { ProductService } from '../../services/product.service';


import { BrandService, Brand } from '../../services/brand.service';

import { CategoryService, Category } from '../../services/category.service';



import {

  ProductRequest,

  ProductVariantRequest

} from '../../models/product';







@Component({

  selector:'app-create-product',

  standalone:true,

  imports:[

    CommonModule,

    FormsModule

  ],

  templateUrl:'./create-product.component.html',

  styleUrl:'./create-product.component.css'

})


export class CreateProductComponent implements OnInit {





  brands:Brand[] = [];


  categories:Category[] = [];






  product:ProductRequest = {



    name:'',


    description:'',


    price:0,


    stock:0,


    imageUrl:'',


    brandId:0,


    categoryId:0,


    variants:[]



  };








  variants:ProductVariantRequest[] = [



    {


      sku:'',

      size:'',

      color:'',

      price:0,

      stock:0


    }



  ];








  constructor(


    private productService:ProductService,


    private brandService:BrandService,


    private categoryService:CategoryService


  ) {}









  ngOnInit():void {


    this.loadBrands();


    this.loadCategories();


  }









  loadBrands():void {



    this.brandService

    .getBrands()

    .subscribe({



      next:(data)=>{


        this.brands=data;


      },



      error:(error)=>{


        console.error(

          'Error cargando marcas',

          error

        );


      }



    });



  }









  loadCategories():void {



    this.categoryService

    .getCategories()

    .subscribe({



      next:(data)=>{


        this.categories=data;


      },



      error:(error)=>{


        console.error(

          'Error cargando categorias',

          error

        );


      }



    });



  }









  addVariant():void {



    this.variants.push({



      sku:'',


      size:'',


      color:'',


      price:0,


      stock:0



    });



  }









  removeVariant(index:number):void {



    this.variants.splice(

      index,

      1

    );



  }









  save():void {



    const request:ProductRequest = {



      ...this.product,


      variants:this.variants



    };








    console.log(

      'PRODUCTO ENVIADO',

      request

    );








    this.productService

    .createProduct(request)

    .subscribe({





      next:(response)=>{



        console.log(

          'PRODUCTO CREADO',

          response

        );



        alert(

          'Producto creado correctamente'

        );



      },







      error:(error)=>{



        console.error(

          'ERROR CREANDO PRODUCTO',

          error

        );



        alert(

          'Error creando producto'

        );



      }





    });



  }







}
