import { ICousin, NewCousin } from './cousin.model';

export const sampleWithRequiredData: ICousin = {
  id: 4236,
};

export const sampleWithPartialData: ICousin = {
  id: 4437,
  ndossierC: 'sitôt que mairie',
  idCousin: 11693,
  placeCousin: 'à défaut de  blême touchant',
  sexeC: 'agréable trop souple',
};

export const sampleWithFullData: ICousin = {
  id: 18661,
  ndossierC: 'prout gâcher au point que',
  idCousin: 19264,
  prenomCousin: 'accorder tant que',
  placeCousin: 'lentement membre de l’équipe',
  sexeC: 'membre à vie',
};

export const sampleWithNewData: NewCousin = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
