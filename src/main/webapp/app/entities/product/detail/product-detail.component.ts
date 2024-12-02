import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IProduct } from '../product.model';

@Component({
  standalone: true,
  selector: 'jhi-product-detail',
  templateUrl: './product-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ProductDetailComponent {
  @Input() product: IProduct | null = null;

  previousState(): void {
    window.history.back();
  }
}
