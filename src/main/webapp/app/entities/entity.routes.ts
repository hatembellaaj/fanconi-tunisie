import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'Authorities' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'androgene',
    data: { pageTitle: 'Androgenes' },
    loadChildren: () => import('./androgene/androgene.routes'),
  },
  {
    path: 'cousin',
    data: { pageTitle: 'Cousins' },
    loadChildren: () => import('./cousin/cousin.routes'),
  },
  {
    path: 'cytogeneticien',
    data: { pageTitle: 'Cytogeneticiens' },
    loadChildren: () => import('./cytogeneticien/cytogeneticien.routes'),
  },
  {
    path: 'cytogenetique',
    data: { pageTitle: 'Cytogenetiques' },
    loadChildren: () => import('./cytogenetique/cytogenetique.routes'),
  },
  {
    path: 'fiche',
    data: { pageTitle: 'Fiches' },
    loadChildren: () => import('./fiche/fiche.routes'),
  },
  {
    path: 'frere',
    data: { pageTitle: 'Freres' },
    loadChildren: () => import('./frere/frere.routes'),
  },
  {
    path: 'hopital',
    data: { pageTitle: 'Hopitals' },
    loadChildren: () => import('./hopital/hopital.routes'),
  },
  {
    path: 'laboratoire',
    data: { pageTitle: 'Laboratoires' },
    loadChildren: () => import('./laboratoire/laboratoire.routes'),
  },
  {
    path: 'medecin',
    data: { pageTitle: 'Medecins' },
    loadChildren: () => import('./medecin/medecin.routes'),
  },
  {
    path: 'membre',
    data: { pageTitle: 'Membres' },
    loadChildren: () => import('./membre/membre.routes'),
  },
  {
    path: 'patient',
    data: { pageTitle: 'Patients' },
    loadChildren: () => import('./patient/patient.routes'),
  },
  {
    path: 'scientifique',
    data: { pageTitle: 'Scientifiques' },
    loadChildren: () => import('./scientifique/scientifique.routes'),
  },
  {
    path: 'service',
    data: { pageTitle: 'Services' },
    loadChildren: () => import('./service/service.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
