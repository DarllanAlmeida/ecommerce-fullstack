import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface Category {

  title: string;

  description: string;

  image: string;

}

@Component({
  selector: 'app-categories',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './categories.component.html',
  styleUrl: './categories.component.css'
})
export class CategoriesComponent {

  categories: Category[] = [

    {
      title: 'RUNNING',
      description: 'Rendimiento para cada kilómetro.',
      image: '/images/categories/running.png'
    },

    {
      title: 'LIFESTYLE',
      description: 'Diseño urbano para tu día a día.',
      image: '/images/categories/lifestyle.png'
    },

    {
      title: 'TRAINING',
      description: 'Potencia cada entrenamiento.',
      image: '/images/categories/training.png'
    }

  ];

}
