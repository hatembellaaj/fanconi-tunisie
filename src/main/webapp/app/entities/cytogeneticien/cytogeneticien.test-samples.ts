import { ICytogeneticien, NewCytogeneticien } from './cytogeneticien.model';

export const sampleWithRequiredData: ICytogeneticien = {
  id: 30193,
};

export const sampleWithPartialData: ICytogeneticien = {
  id: 24552,
  code: 15112,
  nom: 'aussitôt que',
  prenom: 'smack quitte à insulter',
  fax: 'même si gai',
  email: 'Adolphie61@gmail.com',
  photo: '../fake-data/blob/hipster.png',
  photoContentType: 'unknown',
  type: 'smack résonner durant',
  login: 'soulager',
  passwd: 'joliment aimable exprès',
  uRL: 'sauf',
};

export const sampleWithFullData: ICytogeneticien = {
  id: 2865,
  code: 27004,
  nom: 'adversaire au-dedans de euh',
  prenom: 'cuicui délégation de manière à ce que',
  service: 'direction loufoque paf',
  etab: 'dans la mesure où partout tâcher',
  adresse: 'afin de en decà de',
  tel: 'plouf',
  poste: 'entre',
  fax: 'du côté de marron',
  email: 'Adalsinde19@gmail.com',
  photo: '../fake-data/blob/hipster.png',
  photoContentType: 'unknown',
  type: "porte-parole à l'exception de grandement",
  login: 'antagoniste sauf à',
  passwd: 'étant donné que dans la mesure où gigantesque',
  uRL: 'énergique manier dehors',
};

export const sampleWithNewData: NewCytogeneticien = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
