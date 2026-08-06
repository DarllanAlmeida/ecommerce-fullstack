import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductService } from '../../../services/product.service';
import { CartService } from '../../../services/cart.service';

import { Product } from '../../../models/product';

import { ProductCardComponent } from '../../../shared/components/product-card/product-card.component';

@Component({
  selector: 'app-featured-products',
  standalone: true,
  imports: [
    CommonModule,
    ProductCardComponent
  ],
  templateUrl: './featured-products.component.html',
  styleUrl: './featured-products.component.css'
})
export class FeaturedProductsComponent implements OnInit {

  products: Product[] = [];

  loading = false;

  constructor(
    private productService: ProductService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {

    this.loadFeaturedProducts();

  }

  loadFeaturedProducts(): void {

    this.loading = true;

    this.productService.getProducts().subscribe({

      next: (products) => {

        // Solo mostramos 3 productos destacados
        this.products = products.slice(0, 3);

        this.loading = false;

      },

      error: (error) => {

        console.error(error);

        this.loading = false;

      }

    });

  }

  addToCart(product: Product): void {

    this.cartService.addToCart(product);

  }

}