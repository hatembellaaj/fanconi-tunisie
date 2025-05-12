import { IScientifique, NewScientifique } from './scientifique.model';

export const sampleWithRequiredData: IScientifique = {
  id: 756,
};

export const sampleWithPartialData: IScientifique = {
  id: 7102,
  codeSC: 15808,
  serviceSC: 'prévaloir',
  telSC: 'encore si',
  emailSC: 'tout fonctionnaire',
  photoSC: '../fake-data/blob/hipster.png',
  photoSCContentType: 'unknown',
};

export const sampleWithFullData: IScientifique = {
  id: 6441,
  codeSC: 4300,
  nomSC: 'vivace énorme commissionnaire',
  prenomSC: 'communauté étudiante',
  serviceSC: 'terne presque main-d’œuvre',
  centreSC: 'du moment que autrement puisque',
  adresseSC: 'exécuter présidence',
  telSC: 'même empêcher',
  emailSC: 'aïe',
  photoSC: '../fake-data/blob/hipster.png',
  photoSCContentType: 'unknown',
  typeSC: 'afin que d’autant que même si',
  loginSC: 'assez sourire longtemps',
  passwdSC: "bzzz au lieu de d'après",
  uRL: 'police proche pendant que',
};

export const sampleWithNewData: NewScientifique = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
