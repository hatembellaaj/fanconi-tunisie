import { IAndrogene, NewAndrogene } from './androgene.model';

export const sampleWithRequiredData: IAndrogene = {
  id: 3548,
};

export const sampleWithPartialData: IAndrogene = {
  id: 12173,
  mois: 26232,
};

export const sampleWithFullData: IAndrogene = {
  id: 18000,
  nDossierPa: 'personnel paf',
  mois: 18738,
  reponse: 'partenaire pin-pon',
};

export const sampleWithNewData: NewAndrogene = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
