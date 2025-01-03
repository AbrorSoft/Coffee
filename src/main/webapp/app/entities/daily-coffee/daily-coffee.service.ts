import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApplicationConfigService } from '../../core/config/application-config.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DailyCoffeeService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/products');
  constructor() {}
  getAllCoffee(): any {
    return this.http.get(this.resourceUrl);
  }
  getImageByKey(data: string): Observable<any> {
    return this.http.get(`api/file/${data}`, { responseType: 'blob' });
  }
}
