import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

import { Product } from '../models/product';



@Injectable({
  providedIn:'root'
})
export class FavoritesService {



  private storageKey = 'kickzone_favorites';



  private favoritesSubject = new BehaviorSubject<Product[]>(

    this.loadFavorites()

  );



  favorites$ = this.favoritesSubject.asObservable();







  constructor(){}







  private loadFavorites():Product[] {



    const saved = localStorage.getItem(

      this.storageKey

    );



    return saved

      ? JSON.parse(saved)

      : [];



  }







  private saveFavorites(products:Product[]):void {



    localStorage.setItem(

      this.storageKey,

      JSON.stringify(products)

    );



  }









  getFavorites():Product[] {



    return this.favoritesSubject.value;


  }









  addFavorite(product:Product):void {



    const current = this.getFavorites();





    const exists = current.some(

      item => item.id === product.id

    );





    if(!exists){



      const updated = [

        ...current,

        product

      ];



      this.saveFavorites(updated);



      this.favoritesSubject.next(updated);



    }



  }









  removeFavorite(id:number):void {



    const updated =

    this.getFavorites()

    .filter(

      product => product.id !== id

    );





    this.saveFavorites(updated);



    this.favoritesSubject.next(updated);



  }









  toggleFavorite(product:Product):void {



    const exists = this.isFavorite(

      product.id!

    );





    if(exists){


      this.removeFavorite(product.id!);


    }else{


      this.addFavorite(product);


    }



  }









  isFavorite(id:number):boolean {



    return this.getFavorites()

    .some(

      product => product.id === id

    );



  }








  getCount():number {



    return this.getFavorites().length;


  }





}