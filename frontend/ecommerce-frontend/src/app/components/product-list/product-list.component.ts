import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';


import { ProductService } from '../../services/product.service';
import { CartService } from '../../services/cart.service';
import { SearchService } from '../../services/search.service';


import { Product } from '../../models/product';


import { ProductCardComponent } from '../../shared/components/product-card/product-card.component';



@Component({

  selector:'app-product-list',

  standalone:true,

  imports:[

    CommonModule,

    FormsModule,

    RouterLink,

    ProductCardComponent

  ],

  templateUrl:'./product-list.component.html',

  styleUrl:'./product-list.component.css'

})


export class ProductListComponent implements OnInit, OnDestroy {



  products:Product[] = [];



  filteredProducts:Product[] = [];



  search='';



  selectedBrand='Todas';



  selectedCategory='Todas';



  sortOption='default';





  brands:string[]=[

    'Todas',

    'Nike',

    'Adidas',

    'Puma',

    'New Balance'

  ];






  categories:string[]=[

    'Todas',

    'Running',

    'Lifestyle'

  ];






  loading=false;



  error='';





  private searchSubscription?:Subscription;









  constructor(


    private productService:ProductService,


    private cartService:CartService,


    private searchService:SearchService



  ) {}









  ngOnInit():void {



    this.loadProducts();






    this.searchSubscription =

    this.searchService.search$

    .subscribe(value=>{





      this.search=value;



      this.filterProducts();





    });



  }









  loadProducts():void {



    this.loading=true;





    this.productService

    .getProducts()

    .subscribe({



      next:(data:Product[])=>{





        this.products=data;



        this.filterProducts();



        this.loading=false;





      },





      error:(err)=>{



        console.error(err);



        this.error=

        'No se pudieron cargar los productos';



        this.loading=false;



      }



    });



  }









  filterProducts():void {



    const text =

    this.search

    .toLowerCase()

    .trim();







    this.filteredProducts =

    this.products.filter(product=>{






      const matchesSearch =



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

      .includes(text);









      const matchesBrand =



      this.selectedBrand === 'Todas'

      ||

      product.brandName === this.selectedBrand;









      const matchesCategory =



      this.selectedCategory === 'Todas'

      ||

      product.categoryName === this.selectedCategory;







      return (

        matchesSearch

        &&

        matchesBrand

        &&

        matchesCategory

      );





    });








    this.sortProducts();



  }









  sortProducts():void {



    switch(this.sortOption){





      case 'priceAsc':



        this.filteredProducts.sort(

          (a,b)=>a.price-b.price

        );


      break;







      case 'priceDesc':



        this.filteredProducts.sort(

          (a,b)=>b.price-a.price

        );


      break;







      case 'nameAsc':



        this.filteredProducts.sort(

          (a,b)=>

          a.name.localeCompare(b.name)

        );


      break;







      case 'nameDesc':



        this.filteredProducts.sort(

          (a,b)=>

          b.name.localeCompare(a.name)

        );


      break;





      default:


      break;



    }



  }









  clearFilters():void {



    this.search='';



    this.selectedBrand='Todas';



    this.selectedCategory='Todas';



    this.sortOption='default';





    this.searchService.clearSearch();




    this.filterProducts();



  }









  addToCart(product:Product):void {



    this.cartService.addToCart(product);



  }









  deleteProduct(id:number):void {



    const confirmDelete =

    confirm(

      '¿Seguro que quieres borrar este producto?'

    );





    if(!confirmDelete){


      return;


    }







    this.productService

    .deleteProduct(id)

    .subscribe({



      next:()=>{



        alert(

          'Producto eliminado'

        );



        this.loadProducts();



      },





      error:(err)=>{



        console.error(err);



      }





    });



  }









  ngOnDestroy():void {



    this.searchSubscription

    ?.unsubscribe();



  }







}
