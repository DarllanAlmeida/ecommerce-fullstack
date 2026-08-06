import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { ProductService } from '../services/product.service';


@Component({
  selector: 'app-create-product',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './create-product.component.html',
  styleUrl: './create-product.component.css'
})
export class CreateProductComponent {


  product = {

    name: '',

    description: '',

    price: 0,

    stock: 0,

    imageUrl: ''

  };



  constructor(

    private productService: ProductService,

    private router: Router

  ) {}





  createProduct() {


    this.productService.createProduct(this.product)

      .subscribe({


        next:()=>{


          alert('Producto creado correctamente ✔');


          this.router.navigate(['/products']);


        },


        error:(err)=>{


          console.error(

            'ERROR CREANDO PRODUCTO',

            err

          );


        }


      });


  }



}
