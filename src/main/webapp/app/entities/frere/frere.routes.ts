import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import FrereResolve from './route/frere-routing-resolve.service';

const frereRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/frere.component').then(m => m.FrereComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/frere-detail.component').then(m => m.FrereDetailComponent),
    resolve: {
      frere: FrereResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/frere-update.component').then(m => m.FrereUpdateComponent),
    resolve: {
      frere: FrereResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/frere-update.component').then(m => m.FrereUpdateComponent),
    resolve: {
      frere: FrereResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default frereRoute;
