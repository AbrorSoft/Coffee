import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  templateUrl: 'about.component.html',
  standalone: true,
  imports: [RouterLink],
  styleUrl: 'about.component.scss',
})
export class AboutComponent {}
