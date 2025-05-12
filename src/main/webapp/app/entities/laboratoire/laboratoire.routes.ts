import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import LaboratoireResolve from './route/laboratoire-routing-resolve.service';

const laboratoireRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/laboratoire.component').then(m => m.LaboratoireComponent),
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/laboratoire-detail.component').then(m => m.LaboratoireDetailComponent),
    resolve: {
      laboratoire: LaboratoireResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/laboratoire-update.component').then(m => m.LaboratoireUpdateComponent),
    resolve: {
      laboratoire: LaboratoireResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/laboratoire-update.component').then(m => m.LaboratoireUpdateComponent),
    resolve: {
      laboratoire: LaboratoireResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default laboratoireRoute;
