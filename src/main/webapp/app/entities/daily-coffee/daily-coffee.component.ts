import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DailyCoffeeService } from './daily-coffee.service';

@Component({
  templateUrl: 'daily-coffee.component.html',
  standalone: true,
  styleUrl: 'daily-coffee.component.scss',
  imports: [RouterLink],
})
export class DailyCoffeeComponent implements OnInit {
  coffeeList: any;
  constructor(protected getCoffeeService: DailyCoffeeService) {}
  ngOnInit() {
    this.getCoffeeService.getAllCoffee().subscribe(data => {
      this.coffeeList = data;
      console.log(data);
    });
  }
}
