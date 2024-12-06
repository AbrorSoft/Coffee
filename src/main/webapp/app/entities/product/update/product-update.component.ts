import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable, of } from 'rxjs';
import { finalize, takeUntil } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ProductType } from 'app/entities/enumerations/product-type.model';
import { IProduct } from '../product.model';
import { ProductService } from '../service/product.service';
import { ProductFormService, ProductFormGroup } from './product-form.service';

@Component({
  standalone: true,
  selector: 'jhi-product-update',
  templateUrl: './product-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ProductUpdateComponent implements OnInit {
  isSaving = false;
  product: IProduct | null = null;
  productTypeValues = Object.keys(ProductType);

  protected productService = inject(ProductService);
  protected productFormService = inject(ProductFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ProductFormGroup = this.productFormService.createProductFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ product }) => {
      this.product = product;
      if (product) {
        this.updateForm(product);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const product = this.productFormService.getProduct(this.editForm);
    if (product.id !== null) {
      this.subscribeToSaveResponse(this.productService.update(product));
    } else {
      this.subscribeToSaveResponse(this.productService.create(product));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduct>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }
  readAsDataUrl(file: File): Observable<any | null> {
    if (!file) return of(null);

    return new Observable(observer => {
      const reader = new FileReader();
      reader.readAsDataURL(file);

      reader.onload = () => {
        observer.next({
          name: file.name,
          contentType: file.type,
          content: (reader.result as string).split(',')[1],
        });

        observer.complete();
      };

      reader.onerror = () => {
        observer.error(null);
        observer.complete();
      };
    });
  }
  onSelectImage(event: any): void {
    this.editForm.get('imageKey')?.setValue(event.target.files[0].name);
    this.readAsDataUrl(event.target.files[0]).subscribe((file: any | null) => this.setImage(file));
  }

  private setImage(file: any | null): void {
    const imageUrl = this.editForm.get('imageFile');

    if (!file) {
      imageUrl?.setValue(null);
    } else {
      const content = `${file.content}`;
      imageUrl?.setValue(<any>content, { emitEvent: false });
    }
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(product: IProduct): void {
    this.product = product;
    this.productFormService.resetForm(this.editForm, product);
  }
}
