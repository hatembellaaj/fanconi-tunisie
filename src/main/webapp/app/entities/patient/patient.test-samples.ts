import { IPatient, NewPatient } from './patient.model';

export const sampleWithRequiredData: IPatient = {
  id: 26535,
};

export const sampleWithPartialData: IPatient = {
  id: 25059,
  ndossierP: 'en dedans de',
  nom: 'commissionnaire d’autant que venger',
  prenom: 'vorace',
  dateNaissance: 'neutre',
  lieuNaissance: 'tant que',
  sexe: 'recta équipe tellement',
  age: 18926,
};

export const sampleWithFullData: IPatient = {
  id: 7361,
  ndossierP: "géométrique à l'insu de",
  nom: 'meuh mature',
  prenom: 'psitt zzzz gigantesque',
  dateNaissance: 'de toc vorace',
  lieuNaissance: 'antagoniste admirablement',
  sexe: 'coac coac',
  gouvernorat: 'espiègle croire',
  adresse: 'sur de manière à ce que',
  reperes: 'chez',
  tel: 'toc modifier ouf',
  prenomPere: 'louer assurer',
  nomMere: 'définir',
  prenomMere: 'athlète désagréable quant à',
  nomGMP: 'super concurrence',
  nomGMM: 'fort',
  age: 21944,
};

export const sampleWithNewData: NewPatient = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
