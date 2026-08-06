import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';


import {
  Product,
  ProductRequest
} from '../models/product';


import { environment } from '../../environments/environment';



@Injectable({
  providedIn:'root'
})
export class ProductService {



  private apiUrl =
    `${environment.apiUrl}/products`;





  constructor(
    private http:HttpClient
  ) {}








  getProducts():Observable<Product[]> {


    return this.http.get<Product[]>(

      this.apiUrl

    );


  }








  getProductById(
    id:number
  ):Observable<Product> {


    return this.http.get<Product>(

      `${this.apiUrl}/${id}`

    );


  }








  createProduct(
    product:ProductRequest
  ):Observable<Product> {


    return this.http.post<Product>(

      this.apiUrl,

      product

    );


  }








  updateProduct(
    id:number,
    product:ProductRequest
  ):Observable<Product> {


    return this.http.put<Product>(

      `${this.apiUrl}/${id}`,

      product

    );


  }








  deleteProduct(
    id:number
  ):Observable<void> {


    return this.http.delete<void>(

      `${this.apiUrl}/${id}`

    );


  }



}
