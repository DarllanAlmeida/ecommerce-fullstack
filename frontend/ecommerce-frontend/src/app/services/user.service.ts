import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {



  private nameSubject = new BehaviorSubject<string>(

    localStorage.getItem('profileName') || ''

  );



  private imageSubject = new BehaviorSubject<string | null>(

    localStorage.getItem('profileImage')

  );





  profileName$ = this.nameSubject.asObservable();



  profileImage$ = this.imageSubject.asObservable();







  updateProfileName(name:string){


    localStorage.setItem(

      'profileName',

      name

    );


    this.nameSubject.next(name);


  }







  updateProfileImage(image:string){


    localStorage.setItem(

      'profileImage',

      image

    );


    this.imageSubject.next(image);


  }







  getProfileName(){


    return this.nameSubject.value;


  }







  getProfileImage(){


    return this.imageSubject.value;


  }




}