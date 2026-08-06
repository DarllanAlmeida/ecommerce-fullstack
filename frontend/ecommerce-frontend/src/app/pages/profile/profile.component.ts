import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';



@Component({

  selector: 'app-profile',

  standalone: true,

  imports: [

    CommonModule,

    FormsModule,

    RouterLink

  ],

  templateUrl: './profile.component.html',

  styleUrl: './profile.component.css'

})


export class ProfileComponent implements OnInit {



  user:any;



  editing:boolean = false;





  profile = {


    name: '',


    email: '',


    phone: '600 000 000',


    address: 'Madrid, España',


    image: null as string | null


  };







  constructor(

    private authService: AuthService,

    private userService: UserService

  ) {}







  ngOnInit(): void {


    this.loadUser();


  }









  loadUser(): void {



    /*
      Cargar datos guardados
    */


    const savedProfile =

      localStorage.getItem('profileData');





    if(savedProfile){



      this.profile = {


        ...this.profile,


        ...JSON.parse(savedProfile)



      };



    }








    /*
      Cargar usuario desde JWT
    */


    const token = this.authService.getToken();





    if(token){



      const payload = JSON.parse(

        atob(token.split('.')[1])

      );




      this.user = payload;





      if(!this.profile.email){



        this.profile.email =

          payload.sub ||

          payload.email ||

          '';



      }



    }








    /*
      Cargar imagen
    */


    const savedImage =

      localStorage.getItem('profileImage');





    if(savedImage){


      this.profile.image = savedImage;


    }









    /*
      Nombre por defecto
    */


    if(!this.profile.name){



      this.profile.name =

        'Usuario KICKZONE';



    }








    /*
      Sincronizar datos iniciales con Navbar
    */


    this.userService.updateProfileName(

      this.profile.name

    );





    if(this.profile.image){



      this.userService.updateProfileImage(

        this.profile.image

      );


    }



  }









  toggleEdit():void{


    this.editing = !this.editing;


  }









  changeImage(event:any):void {



    const file = event.target.files[0];





    if(!file){


      return;


    }








    if(

      file.type !== 'image/jpeg' &&

      file.type !== 'image/png'

    ){



      alert(

        'Solo se permiten imágenes JPG o PNG'

      );



      return;


    }








    const reader = new FileReader();






    reader.onload = () => {



      const image = reader.result as string;






      this.profile.image = image;







      this.userService.updateProfileImage(

        image

      );



    };








    reader.readAsDataURL(file);



  }









  saveProfile():void {





    /*
      Guardar perfil completo
    */


    localStorage.setItem(

      'profileData',

      JSON.stringify(this.profile)

    );








    /*
      Actualizar nombre Navbar
    */


    this.userService.updateProfileName(

      this.profile.name

    );









    /*
      Actualizar imagen Navbar
    */


    if(this.profile.image){



      this.userService.updateProfileImage(

        this.profile.image

      );


    }








    this.editing = false;



  }







}
