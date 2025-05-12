import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import MembreResolve from './route/membre-routing-resolve.service';

const membreRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/membre.component').then(m => m.MembreComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/membre-detail.component').then(m => m.MembreDetailComponent),
    resolve: {
      membre: MembreResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/membre-update.component').then(m => m.MembreUpdateComponent),
    resolve: {
      membre: MembreResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/membre-update.component').then(m => m.MembreUpdateComponent),
    resolve: {
      membre: MembreResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default membreRoute;
