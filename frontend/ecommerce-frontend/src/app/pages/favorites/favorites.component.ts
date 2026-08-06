import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { FavoritesService } from '../../services/favorites.service';
import { CartService } from '../../services/cart.service';

import { Product } from '../../models/product';



@Component({

  selector:'app-favorites',

  standalone:true,

  imports:[

    CommonModule,

    RouterLink

  ],

  templateUrl:'./favorites.component.html',

  styleUrl:'./favorites.component.css'

})


export class FavoritesComponent implements OnInit {



  favorites:Product[] = [];





  constructor(

    private favoritesService:FavoritesService,

    private cartService:CartService

  ){}





  ngOnInit():void {



    this.loadFavorites();



    this.favoritesService.favorites$

    .subscribe(data=>{


      this.favorites = data;


    });



  }







  loadFavorites():void {



    this.favorites =

    this.favoritesService

    .getFavorites();



  }







  removeFavorite(id:number):void {



    this.favoritesService

    .removeFavorite(id);



  }








  addToCart(product:Product):void {



    this.cartService

    .addToCart(product);



  }



}
