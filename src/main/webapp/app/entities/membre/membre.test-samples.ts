import { IMembre, NewMembre } from './membre.model';

export const sampleWithRequiredData: IMembre = {
  id: 10477,
};

export const sampleWithPartialData: IMembre = {
  id: 25756,
  prenomM: 'lors de',
  lienParente: 'antique nier',
  ageDecouverteM: 21945,
};

export const sampleWithFullData: IMembre = {
  id: 29010,
  nDossierM: 'électorat hebdomadaire',
  idMembre: 1961,
  prenomM: 'parce que',
  lienParente: 'touchant',
  typeCancerM: 'toc',
  ageDecouverteM: 25966,
};

export const sampleWithNewData: NewMembre = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
