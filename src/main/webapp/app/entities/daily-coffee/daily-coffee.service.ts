import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApplicationConfigService } from '../../core/config/application-config.service';

@Injectable({
  providedIn: 'root',
})
export class DailyCoffeeService {
  constructor() {}
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/products');
  getAllCoffee() {
    return this.http.get(this.resourceUrl);
  }
}
