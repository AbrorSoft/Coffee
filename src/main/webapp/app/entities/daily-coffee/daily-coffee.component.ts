import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DailyCoffeeService } from './daily-coffee.service';
import { NgOptimizedImage } from '@angular/common';
import { DailyCoffeeModel } from './daily-coffee.model';

@Component({
  templateUrl: 'daily-coffee.component.html',
  standalone: true,
  styleUrl: 'daily-coffee.component.scss',
  imports: [RouterLink, NgOptimizedImage],
})
export class DailyCoffeeComponent implements OnInit {
  coffeeList: DailyCoffeeModel[] | undefined;
  constructor(protected getCoffeeService: DailyCoffeeService) {}
  ngOnInit() {
    this.getCoffeeService.getAllCoffee().subscribe((data: any) => {
      this.coffeeList = data;
      console.log(data);
    });
  }
}
