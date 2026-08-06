import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './product-form.component.html'
})
export class ProductFormComponent {

  productForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private service: ProductService,
    private router: Router
  ) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0.01)]],
      description: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.productForm.invalid) {
      alert('Rellena todos los campos correctamente ⚠');
      return;
    }

    this.service.createProduct(this.productForm.value).subscribe({
      next: () => {
        alert('Producto creado correctamente ✔');
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error('ERROR AL CREAR PRODUCTO:', err);
        alert('Error al crear el producto ❌');
      }
    });
  }
}