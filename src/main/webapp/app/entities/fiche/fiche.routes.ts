import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import FicheResolve from './route/fiche-routing-resolve.service';

const ficheRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/fiche.component').then(m => m.FicheComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/fiche-detail.component').then(m => m.FicheDetailComponent),
    resolve: {
      fiche: FicheResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/fiche-update.component').then(m => m.FicheUpdateComponent),
    resolve: {
      fiche: FicheResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/fiche-update.component').then(m => m.FicheUpdateComponent),
    resolve: {
      fiche: FicheResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default ficheRoute;
