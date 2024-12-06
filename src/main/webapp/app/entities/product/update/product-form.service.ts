import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IProduct, NewProduct } from '../product.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IProduct for edit and NewProductFormGroupInput for create.
 */
type ProductFormGroupInput = IProduct | PartialWithRequiredKeyOf<NewProduct>;

type ProductFormDefaults = Pick<NewProduct, 'id' | 'isDiet'>;

type ProductFormGroupContent = {
  id: FormControl<IProduct['id'] | NewProduct['id']>;
  name: FormControl<IProduct['name']>;
  description: FormControl<IProduct['description']>;
  price: FormControl<IProduct['price']>;
  imageKey: FormControl<IProduct['imageKey']>;
  gram: FormControl<IProduct['gram']>;
  calories: FormControl<IProduct['calories']>;
  type: FormControl<IProduct['type']>;
  isDiet: FormControl<IProduct['isDiet']>;
  imageFile: FormControl<IProduct['imageFile']>;
};

export type ProductFormGroup = FormGroup<ProductFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ProductFormService {
  createProductFormGroup(product: ProductFormGroupInput = { id: null }): ProductFormGroup {
    const productRawValue = {
      ...this.getFormDefaults(),
      ...product,
    };
    return new FormGroup<ProductFormGroupContent>({
      id: new FormControl(
        { value: productRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      name: new FormControl(productRawValue.name, {
        validators: [Validators.required, Validators.minLength(2), Validators.maxLength(255)],
      }),
      description: new FormControl(productRawValue.description),
      price: new FormControl(productRawValue.price, {
        validators: [Validators.required, Validators.min(0)],
      }),
      imageKey: new FormControl(productRawValue.imageKey),
      imageFile: new FormControl(productRawValue.imageFile),
      gram: new FormControl(productRawValue.gram, {
        validators: [Validators.required],
      }),
      calories: new FormControl(productRawValue.calories),
      type: new FormControl(productRawValue.type),
      isDiet: new FormControl(productRawValue.isDiet, {
        validators: [Validators.required],
      }),
    });
  }

  getProduct(form: ProductFormGroup): IProduct | NewProduct {
    return form.getRawValue() as IProduct | NewProduct;
  }

  resetForm(form: ProductFormGroup, product: ProductFormGroupInput): void {
    const productRawValue = { ...this.getFormDefaults(), ...product };
    form.reset(
      {
        ...productRawValue,
        id: { value: productRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ProductFormDefaults {
    return {
      id: null,
      isDiet: false,
    };
  }
}
