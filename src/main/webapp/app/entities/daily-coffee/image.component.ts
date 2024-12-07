import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { DailyCoffeeService } from './daily-coffee.service';

@Component({
  selector: 'app-image',
  template: `
    <img [src]="imageSrc" alt="Loaded Image" *ngIf="imageSrc; else loading" />
    <ng-template #loading>
      {{ imageSrc }}
      <h1>loading</h1>
      <p>Loading...</p>
    </ng-template>
  `,
  standalone: true,
})
export class ImageComponent implements OnChanges {
  // look here imageKey coming null
  @Input() imageKey: any; // Input from the parent component
  imageSrc: string | undefined; // Stores the fetched image URL

  constructor(private getCoffeeService: DailyCoffeeService) {}

  // Reacts to changes in `imageKey`
  ngOnChanges(changes: SimpleChanges): void {
    console.log();
    if (changes['imageKey'] && this.imageKey) {
      this.fetchImage(this.imageKey);
    }
  }

  private fetchImage(imageKey: string): void {
    this.getCoffeeService.getImageByKey(imageKey).subscribe({
      next: data => {
        this.imageSrc = data; // Adjust if the API response structure is different
      },
      error: err => {
        console.error('Failed to load image:', err);
        this.imageSrc = 'path/to/fallback/image.jpg'; // Optional fallback
      },
    });
  }
}
