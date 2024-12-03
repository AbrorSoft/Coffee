import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'coffeeApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'about',
    data: { pageTitle: 'coffeeApp.adminAuthority.home.title' },
    loadChildren: () => import('./About/about.routes'),
  },
  {
    path: 'daily-coffee',
    data: { pageTitle: 'coffeeApp.adminAuthority.home.title' },
    loadChildren: () => import('./daily-coffee/daily-coffee.routes'),
  },
  {
    path: 'product',
    data: { pageTitle: 'coffeeApp.product.home.title' },
    loadChildren: () => import('./product/product.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
