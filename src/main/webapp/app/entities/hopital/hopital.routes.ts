import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import HopitalResolve from './route/hopital-routing-resolve.service';

const hopitalRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/hopital.component').then(m => m.HopitalComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/hopital-detail.component').then(m => m.HopitalDetailComponent),
    resolve: {
      hopital: HopitalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/hopital-update.component').then(m => m.HopitalUpdateComponent),
    resolve: {
      hopital: HopitalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/hopital-update.component').then(m => m.HopitalUpdateComponent),
    resolve: {
      hopital: HopitalResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default hopitalRoute;
