import { IFrere, NewFrere } from './frere.model';

export const sampleWithRequiredData: IFrere = {
  id: 3529,
};

export const sampleWithPartialData: IFrere = {
  id: 30335,
  age: 5069,
};

export const sampleWithFullData: IFrere = {
  id: 30556,
  nDossierF: 'près actionnaire au-delà',
  idFrere: 14809,
  prenomFrere: 'aimable longtemps chut',
  atteint: 'si bien que',
  placefratrie: 31077,
  sexeF: 'membre de l’équipe membre de l’équipe',
  decedes: 'apte',
  age: 10615,
};

export const sampleWithNewData: NewFrere = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
