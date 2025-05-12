import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import CousinResolve from './route/cousin-routing-resolve.service';

const cousinRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/cousin.component').then(m => m.CousinComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/cousin-detail.component').then(m => m.CousinDetailComponent),
    resolve: {
      cousin: CousinResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/cousin-update.component').then(m => m.CousinUpdateComponent),
    resolve: {
      cousin: CousinResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/cousin-update.component').then(m => m.CousinUpdateComponent),
    resolve: {
      cousin: CousinResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default cousinRoute;
