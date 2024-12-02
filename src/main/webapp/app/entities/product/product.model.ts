import { ProductType } from 'app/entities/enumerations/product-type.model';

export interface IProduct {
  id: number;
  name?: string | null;
  description?: string | null;
  price?: number | null;
  imageKey?: string | null;
  gram?: string | null;
  calories?: string | null;
  type?: keyof typeof ProductType | null;
  isDiet?: boolean | null;
}

export type NewProduct = Omit<IProduct, 'id'> & { id: null };
