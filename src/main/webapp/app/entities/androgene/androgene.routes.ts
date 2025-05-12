import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import AndrogeneResolve from './route/androgene-routing-resolve.service';

const androgeneRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/androgene.component').then(m => m.AndrogeneComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/androgene-detail.component').then(m => m.AndrogeneDetailComponent),
    resolve: {
      androgene: AndrogeneResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/androgene-update.component').then(m => m.AndrogeneUpdateComponent),
    resolve: {
      androgene: AndrogeneResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/androgene-update.component').then(m => m.AndrogeneUpdateComponent),
    resolve: {
      androgene: AndrogeneResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default androgeneRoute;
