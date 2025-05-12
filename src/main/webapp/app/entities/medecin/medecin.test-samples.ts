import { IMedecin, NewMedecin } from './medecin.model';

export const sampleWithRequiredData: IMedecin = {
  id: 12283,
};

export const sampleWithPartialData: IMedecin = {
  id: 24107,
  cIN: 28978,
  nomMedecin: 'bientôt nonobstant snif',
  prenomMedecin: 'sage minuscule autour de',
  telM: 'pourpre',
  faxM: 'choir calme psitt',
  emailM: 'durer du moment que pressentir',
  uRL: 'loin',
};

export const sampleWithFullData: IMedecin = {
  id: 8512,
  cIN: 23454,
  nomMedecin: 'viser',
  prenomMedecin: 'ouah administration',
  grade: 'prout sauvage ci',
  typeMedecin: 'de crainte que collègue délégation',
  gouvernoratM: 'à demi',
  adresseM: 'aux environs de avant',
  telM: 'biathlète',
  posteM: 'concurrence',
  faxM: 'à bas de',
  emailM: 'dès que puisque',
  hopital: 'franco adorable',
  service: 'sans que tout à fait bzzz',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
  login: 'rectorat avare',
  passwd: 'outre confier',
  uRL: 'de peur que de la part de',
};

export const sampleWithNewData: NewMedecin = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
