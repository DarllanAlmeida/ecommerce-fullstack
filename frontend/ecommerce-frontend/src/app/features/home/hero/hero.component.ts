import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { ProductService } from '../../../services/product.service';
import { Product } from '../../../models/product';

@Component({
  selector: 'app-hero',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './hero.component.html',
  styleUrl: './hero.component.css'
})
export class HeroComponent implements OnInit, OnDestroy {

  products: Product[] = [];

  current = 0;

  intervalId?: ReturnType<typeof setInterval>;

  constructor(
    private productService: ProductService
  ) {}

  ngOnInit(): void {

    this.loadProducts();

  }

  ngOnDestroy(): void {

    if(this.intervalId){

      clearInterval(this.intervalId);

    }

  }

  loadProducts(): void {

    this.productService.getProducts().subscribe({

      next: (products: Product[]) => {

        this.products = products.slice(0,4);

        if(this.products.length){

          this.startCarousel();

        }

      },

      error: (err) => {

        console.error(err);

      }

    });

  }

  startCarousel(): void {

    if(this.intervalId){

      clearInterval(this.intervalId);

    }

    this.intervalId = setInterval(() => {

      this.next();

    },6000);

  }

  next(): void {

    if(!this.products.length){

      return;

    }

    this.current =

      (this.current + 1)

      %

      this.products.length;

  }

  previous(): void {

    if(!this.products.length){

      return;

    }

    this.current =

      (

        this.current - 1 +

        this.products.length

      )

      %

      this.products.length;

  }

  goTo(index:number):void{

    this.current=index;

  }

}
