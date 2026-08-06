import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';



export interface Brand {

  id:number;

  name:string;

}





@Injectable({
  providedIn:'root'
})
export class BrandService {



  private apiUrl =

    `${environment.apiUrl}/brands`;





  constructor(

    private http:HttpClient

  ){}





  getBrands():Observable<Brand[]> {


    return this.http.get<Brand[]>(

      this.apiUrl

    );


  }



}