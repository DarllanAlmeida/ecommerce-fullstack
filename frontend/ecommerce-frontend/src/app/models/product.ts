export interface Product {


    id:number;

    name:string;

    description:string;

    price:number;

    stock:number;

    imageUrl:string;

    brandId:number;

    brandName:string;

    categoryId:number;

    categoryName:string;

    variants:ProductVariant[];


}







export interface ProductVariant {


    id:number;

    sku:string;

    size:string;

    color:string;

    price:number;

    stock:number;


}







export interface ProductRequest {


    name:string;

    description:string;

    price:number;

    stock:number;

    imageUrl:string;

    brandId:number;

    categoryId:number;

    variants:ProductVariantRequest[];


}







export interface ProductVariantRequest {


    id?:number;

    sku:string;

    size:string;

    color:string;

    price:number;

    stock:number;


}