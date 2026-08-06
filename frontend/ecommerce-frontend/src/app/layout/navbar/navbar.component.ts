import { Component, HostListener, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { FormsModule } from '@angular/forms';


import { CartService } from '../../services/cart.service';
import { AuthService } from '../../services/auth.service';
import { FavoritesService } from '../../services/favorites.service';
import { SearchService } from '../../services/search.service';
import { ProductService } from '../../services/product.service';


import { Product } from '../../models/product';



@Component({

  selector:'app-navbar',

  standalone:true,

  imports:[

    CommonModule,

    RouterLink,

    RouterLinkActive,

    FormsModule

  ],

  templateUrl:'./navbar.component.html',

  styleUrl:'./navbar.component.css'

})


export class NavbarComponent implements OnInit {



  mobileMenu=false;


  scrolled=false;


  showSearch=false;


  showUserMenu=false;



  searchText='';



  products:Product[]=[];


  searchResults:Product[]=[];





  profileImage:string|null=null;


  profileName='';



  favoriteCount=0;







  constructor(


    public cartService:CartService,


    public authService:AuthService,


    private favoritesService:FavoritesService,


    private searchService:SearchService,


    private productService:ProductService,


    private router:Router


  ) {}








  ngOnInit():void {



    this.loadProfileData();



    this.loadProducts();




    this.favoritesService.favorites$

    .subscribe(data=>{


      this.favoriteCount=data.length;


    });



  }









  loadProducts(){



    this.productService

    .getProducts()

    .subscribe({


      next:(data)=>{


        this.products=data;


      },


      error:(err)=>{


        console.error(err);


      }


    });


  }









  search(){



    const text=

    this.searchText

    .toLowerCase()

    .trim();





    if(!text){


      this.searchResults=[];


      return;


    }







    this.searchResults=

    this.products

    .filter(product=>



      product.name

      .toLowerCase()

      .includes(text)



      ||

      (product.brandName ?? '')

      .toLowerCase()

      .includes(text)



      ||

      (product.categoryName ?? '')

      .toLowerCase()

      .includes(text)



    )

    .slice(0,5);






    this.searchService.setSearch(text);



  }









  goProduct(id:number){



    this.showSearch=false;


    this.searchText='';


    this.searchResults=[];




    this.router.navigate([

      '/products',

      id

    ]);



  }









  toggleSearch(){



    this.showSearch=!this.showSearch;


    this.showUserMenu=false;


  }








  toggleUserMenu(){



    this.showUserMenu=

    !this.showUserMenu;



    this.showSearch=false;



  }








  toggleMenu(){


    this.mobileMenu=

    !this.mobileMenu;



  }









  closeMenu(){


    this.mobileMenu=false;


  }








  loadProfileData(){



    this.profileImage=

    localStorage.getItem(

      'profileImage'

    );





    const saved=

    localStorage.getItem(

      'profileData'

    );





    if(saved){



      const profile=

      JSON.parse(saved);



      this.profileName=

      profile.name || '';



    }



  }









  get cartCount(){


    return this.cartService.getCartCount();


  }








  logout(){



    this.authService.logout();



    localStorage.removeItem(

      'profileImage'

    );


    localStorage.removeItem(

      'profileData'

    );



    window.location.href='/login';



  }









  @HostListener('window:scroll')

  onScroll(){


    this.scrolled=

    window.scrollY > 40;



  }



}
