import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import { ProductVariant } from '../models/product-variant';

@Injectable({
  providedIn: 'root'
})
export class ProductVariantService {

  private apiUrl = `${environment.apiUrl}/product-variants`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<ProductVariant[]> {

    return this.http.get<ProductVariant[]>(this.apiUrl);

  }

  getById(id: number): Observable<ProductVariant> {

    return this.http.get<ProductVariant>(
      `${this.apiUrl}/${id}`
    );

  }

  getByProduct(productId: number): Observable<ProductVariant[]> {

    return this.http.get<ProductVariant[]>(
      `${this.apiUrl}/product/${productId}`
    );

  }

}