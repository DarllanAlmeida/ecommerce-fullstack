import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeroComponent } from '../hero/hero.component';
import { CategoriesComponent } from '../categories/categories.component';
import { FeaturedProductsComponent } from '../featured-products/featured-products.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    HeroComponent,
    CategoriesComponent,
    FeaturedProductsComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
