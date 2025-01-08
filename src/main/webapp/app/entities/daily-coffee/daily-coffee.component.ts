import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DailyCoffeeService } from './daily-coffee.service';
import { NgClass, NgOptimizedImage } from '@angular/common';
import { DailyCoffeeModel } from './daily-coffee.model';
import { ProductService } from '../product/service/product.service';
import { ImageComponent } from './image.component';

@Component({
  templateUrl: './daily-coffee.component.html',
  standalone: true,
  styleUrl: 'daily-coffee.component.scss',
  imports: [RouterLink, NgOptimizedImage, ImageComponent, NgClass],
})
export class DailyCoffeeComponent implements OnInit {
  coffeeList: DailyCoffeeModel[] | undefined;
  constructor(protected getCoffeeService: DailyCoffeeService) {}
  ngOnInit(): any {
    this.getCoffeeService.getAllCoffee().subscribe((data: any) => {
      this.coffeeList = data;
    });
  }
}
