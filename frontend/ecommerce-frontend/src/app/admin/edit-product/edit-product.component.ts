import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';


import { ProductService } from '../../services/product.service';


import { BrandService, Brand } from '../../services/brand.service';

import { CategoryService, Category } from '../../services/category.service';



import {
  Product,
  ProductRequest,
  ProductVariantRequest
} from '../../models/product';







@Component({

  selector:'app-edit-product',

  standalone:true,

  imports:[

    CommonModule,

    FormsModule

  ],

  templateUrl:'./edit-product.component.html',

  styleUrl:'./edit-product.component.css'

})


export class EditProductComponent implements OnInit {



  id!:number;


  loading:boolean = true;



  product!:ProductRequest;



  variants:ProductVariantRequest[] = [];



  brands:Brand[] = [];


  categories:Category[] = [];









  constructor(


    private route:ActivatedRoute,


    private router:Router,


    private productService:ProductService,


    private brandService:BrandService,


    private categoryService:CategoryService


  ) {}









  ngOnInit():void {



    this.id = Number(

      this.route.snapshot.paramMap.get('id')

    );



    this.loadBrands();

    this.loadCategories();

    this.loadProduct();



  }









  loadBrands():void {



    this.brandService

    .getBrands()

    .subscribe({



      next:(data:Brand[])=>{


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



      next:(data:Category[])=>{


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









  loadProduct():void {



    this.productService

    .getProductById(this.id)

    .subscribe({



      next:(data:Product)=>{





        this.product = {



          name:data.name,


          description:data.description,


          price:data.price,


          stock:data.stock,


          imageUrl:data.imageUrl,


          brandId:data.brandId,


          categoryId:data.categoryId,


          variants:[]



        };









        this.variants = data.variants.map(v => ({



          id:v.id,


          sku:v.sku,


          size:v.size,


          color:v.color,


          price:v.price,


          stock:v.stock



        }));







        this.loading=false;




      },





      error:(error)=>{



        console.error(

          'Error cargando producto',

          error

        );


        this.loading=false;



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



      name:this.product.name,


      description:this.product.description,


      price:this.product.price,


      stock:this.product.stock,


      imageUrl:this.product.imageUrl,


      brandId:this.product.brandId,


      categoryId:this.product.categoryId,


      variants:this.variants



    };







    console.log(

      'UPDATE ENVIADO',

      request

    );








    this.productService

    .updateProduct(

      this.id,

      request

    )

    .subscribe({





      next:(response)=>{



        console.log(

          'PRODUCTO ACTUALIZADO',

          response

        );



        alert(

          'Producto actualizado correctamente'

        );



        this.router.navigate(

          ['/admin/products']

        );



      },







      error:(error)=>{



        console.error(

          'ERROR ACTUALIZANDO PRODUCTO',

          error

        );



        alert(

          'Error actualizando producto'

        );



      }





    });



  }







}