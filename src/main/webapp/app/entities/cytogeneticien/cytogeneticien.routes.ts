import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import CytogeneticienResolve from './route/cytogeneticien-routing-resolve.service';

const cytogeneticienRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/cytogeneticien.component').then(m => m.CytogeneticienComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/cytogeneticien-detail.component').then(m => m.CytogeneticienDetailComponent),
    resolve: {
      cytogeneticien: CytogeneticienResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/cytogeneticien-update.component').then(m => m.CytogeneticienUpdateComponent),
    resolve: {
      cytogeneticien: CytogeneticienResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/cytogeneticien-update.component').then(m => m.CytogeneticienUpdateComponent),
    resolve: {
      cytogeneticien: CytogeneticienResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default cytogeneticienRoute;
