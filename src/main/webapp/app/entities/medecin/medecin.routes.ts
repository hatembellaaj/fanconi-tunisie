import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import MedecinResolve from './route/medecin-routing-resolve.service';

const medecinRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/medecin.component').then(m => m.MedecinComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/medecin-detail.component').then(m => m.MedecinDetailComponent),
    resolve: {
      medecin: MedecinResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/medecin-update.component').then(m => m.MedecinUpdateComponent),
    resolve: {
      medecin: MedecinResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/medecin-update.component').then(m => m.MedecinUpdateComponent),
    resolve: {
      medecin: MedecinResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default medecinRoute;
