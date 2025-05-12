import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import CytogenetiqueResolve from './route/cytogenetique-routing-resolve.service';

const cytogenetiqueRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/cytogenetique.component').then(m => m.CytogenetiqueComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/cytogenetique-detail.component').then(m => m.CytogenetiqueDetailComponent),
    resolve: {
      cytogenetique: CytogenetiqueResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/cytogenetique-update.component').then(m => m.CytogenetiqueUpdateComponent),
    resolve: {
      cytogenetique: CytogenetiqueResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/cytogenetique-update.component').then(m => m.CytogenetiqueUpdateComponent),
    resolve: {
      cytogenetique: CytogenetiqueResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default cytogenetiqueRoute;
