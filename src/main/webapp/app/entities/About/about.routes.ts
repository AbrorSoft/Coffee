import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';

import { AboutComponent } from './about.component';

const aboutRoute: Routes = [
  {
    path: '',
    component: AboutComponent,
  },
];

export default aboutRoute;
