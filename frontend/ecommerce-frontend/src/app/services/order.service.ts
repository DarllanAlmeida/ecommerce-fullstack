import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';







export interface OrderItem {


  id?:number;

  productId?:number;

  productVariantId?:number;

  productName:string;

  sku?:string;

  quantity:number;

  price?:number;

  unitPrice?:number;

  subtotal?:number;

  size?:string;

  color?:string;


}








export interface Order {


  id:number;

  customerId?:number;

  customerName?:string;

  createdAt:string;

  subtotal?:number;

  discount?:number;

  shipping?:number;

  total:number;

  status:string;

  items:OrderItem[];


}







export interface OrderHistory {


  oldStatus:string | null;

  newStatus:string;

  changedAt:string;


}








@Injectable({
  providedIn:'root'
})
export class OrderService {





  private apiUrl =

    `${environment.apiUrl}/orders`;









  constructor(

    private http:HttpClient

  ) {}









  // CREAR PEDIDO CHECKOUT


  createOrder(

    customerId:number,

    addressId:number

  ):Observable<Order> {


    return this.http.post<Order>(

      `${this.apiUrl}/checkout`,

      {

        customerId,

        addressId

      }

    );


  }









  // TODOS LOS PEDIDOS


  getOrders():Observable<Order[]> {


    return this.http.get<Order[]>(

      this.apiUrl

    );


  }









  // PEDIDO POR ID


  getOrderById(

    id:number

  ):Observable<Order> {


    return this.http.get<Order>(

      `${this.apiUrl}/${id}`

    );


  }









  // PEDIDOS CLIENTE


  getOrdersByCustomer(

    customerId:number

  ):Observable<Order[]> {


    return this.http.get<Order[]>(

      `${this.apiUrl}/customer/${customerId}`

    );


  }









  // HISTORIAL


  getHistory(

    id:number

  ):Observable<OrderHistory[]> {


    return this.http.get<OrderHistory[]>(

      `${this.apiUrl}/${id}/history`

    );


  }









  // CANCELAR PEDIDO


  cancelOrder(

    id:number

  ):Observable<Order> {


    return this.http.put<Order>(

      `${this.apiUrl}/${id}/cancel`,

      {}

    );


  }









  // =========================
  // CAMBIAR ESTADO PEDIDO
  // =========================


  updateStatus(

    id:number,

    status:string

  ):Observable<Order> {


    return this.http.put<Order>(

      `${this.apiUrl}/${id}/status?status=${status}`,

      {}

    );


  }






}