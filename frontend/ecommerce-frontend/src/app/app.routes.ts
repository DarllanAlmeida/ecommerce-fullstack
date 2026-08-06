import { Routes } from '@angular/router';


// HOME

import { HomeComponent } from './features/home/home/home.component';


// PRODUCTOS

import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductDetailComponent } from './pages/product-detail/product-detail.component';



// CARRITO

import { CartComponent } from './cart/cart.component';



// PEDIDOS CLIENTE

import { OrderSuccessComponent } from './order-success/order-success.component';
import { OrdersComponent } from './orders/orders.component';
import { OrderDetailComponent } from './order-detail/order-detail.component';



// AUTH

import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';



// PERFIL

import { ProfileComponent } from './pages/profile/profile.component';



// FAVORITOS

import { FavoritesComponent } from './pages/favorites/favorites.component';



// ADMIN

import { AdminLayoutComponent } 
from './admin/admin-layout/admin-layout.component';

import { DashboardComponent } 
from './admin/dashboard/dashboard.component';

import { ProductsComponent } 
from './admin/products/products.component';

import { EditProductComponent } 
from './admin/edit-product/edit-product.component';

import { OrdersComponent as AdminOrdersComponent } 
from './admin/orders/orders.component';

import { CreateProductComponent } 
from './admin/create-product/create-product.component';



// GUARD

import { authGuard } 
from './core/guards/auth.guard';







export const routes: Routes = [



  // =========================
  // HOME
  // =========================


  {
    path:'',
    component:HomeComponent
  },





  // =========================
  // PRODUCTOS
  // =========================


  {
    path:'products',
    component:ProductListComponent
  },



  {
    path:'products/:id',
    component:ProductDetailComponent
  },







  // =========================
  // AUTH
  // =========================


  {
    path:'login',
    component:LoginComponent
  },



  {
    path:'register',
    component:RegisterComponent
  },








  // =========================
  // PERFIL
  // =========================


  {
    path:'profile',
    component:ProfileComponent,
    canActivate:[authGuard]
  },








  // =========================
  // FAVORITOS
  // =========================


  {
    path:'favorites',
    component:FavoritesComponent,
    canActivate:[authGuard]
  },








  // =========================
  // CARRITO
  // =========================


  {
    path:'cart',
    component:CartComponent,
    canActivate:[authGuard]
  },








  // =========================
  // PEDIDOS CLIENTE
  // =========================


  {
    path:'order-success',
    component:OrderSuccessComponent,
    canActivate:[authGuard]
  },



  {
    path:'orders',
    component:OrdersComponent,
    canActivate:[authGuard]
  },



  {
    path:'orders/:id',
    component:OrderDetailComponent,
    canActivate:[authGuard]
  },









  // =========================
  // PANEL ADMINISTRADOR
  // =========================


  {
    path:'admin',

    component:AdminLayoutComponent,

    canActivate:[authGuard],


    children:[



      {
        path:'',

        component:DashboardComponent

      },



      {
        path:'products',

        component:ProductsComponent

      },



      {
        path:'products/create',

        component:CreateProductComponent

      },



      {
        path:'products/edit/:id',

        component:EditProductComponent

      },



      {
        path:'orders',

        component:AdminOrdersComponent

      }



    ]

  },









  // =========================
  // ERROR
  // =========================


  {
    path:'**',
    redirectTo:''
  }



];
