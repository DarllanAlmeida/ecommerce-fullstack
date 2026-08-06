import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn:'root'
})
export class SearchService {



  private searchSubject = new BehaviorSubject<string>('');


  search$ = this.searchSubject.asObservable();






  constructor(){}







  setSearch(value:string):void {



    this.searchSubject.next(

      value.trim().toLowerCase()

    );



  }







  clearSearch():void {



    this.searchSubject.next('');



  }



}
