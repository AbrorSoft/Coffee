import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { DailyCoffeeService } from './daily-coffee.service';

@Component({
  selector: 'app-image',
  template: `<img [src]="imageSrc" alt="Loaded Image" /> `,
  styles: `
    img {
      width: 100%;
      object-fit: cover;
      height: 100%;
    }
  `,
  standalone: true,
})
export class ImageComponent implements OnChanges {
  @Input() imageKey: any; // Input from the parent component
  imageSrc: string | undefined; // Stores the fetched image URL

  constructor(private getCoffeeService: DailyCoffeeService) {}
  // Reacts to changes in `imageKey`
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['imageKey'] && this.imageKey) {
      this.fetchImage(this.imageKey);
    }
  }
  private fetchImage(imageKey: string): void {
    this.getCoffeeService.getImageByKey(imageKey).subscribe({
      next: data => {
        this.imageSrc = URL.createObjectURL(data);
        console.log(this.imageSrc, 'image'); // Adjust if the API response structure is different
      },
      error: err => {
        console.error('Failed to load image:', err);
        this.imageSrc = 'path/to/fallback/image.jpg'; // Optional fallback
      },
    });
  }
}
