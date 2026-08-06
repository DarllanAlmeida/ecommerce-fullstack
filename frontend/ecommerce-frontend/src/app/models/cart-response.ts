import { CartItem } from './cart-item';

export interface CartResponse {

  id: number;

  customerId: number;

  customerName: string;

  subtotal: number;

  discount: number;

  shipping: number;

  total: number;

  items: CartItem[];

}