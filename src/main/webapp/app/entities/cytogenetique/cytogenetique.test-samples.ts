import dayjs from 'dayjs/esm';

import { ICytogenetique, NewCytogenetique } from './cytogenetique.model';

export const sampleWithRequiredData: ICytogenetique = {
  id: 28779,
};

export const sampleWithPartialData: ICytogenetique = {
  id: 3931,
  laboratoire: 'autrement',
  agentPontant: 'vers',
  instabilitePourcentage: 23153.94,
  iRPourcentage: 15106.29,
  moelle: "frotter jamais à l'encontre de",
  resultatMoelle: 'bien que complètement',
};

export const sampleWithFullData: ICytogenetique = {
  id: 27128,
  nDossierPatient: 'saluer communauté étudiante gratis',
  nEtudeCyto: 19173,
  lymphocytes: 'perplexe',
  dateSang: dayjs('2025-05-12'),
  laboratoire: 'coudre hors fonctionnaire',
  agentPontant: 'contre entre-temps après-demain',
  instabilite: 'outre à la merci chez',
  instabilitePourcentage: 689.11,
  iR: 'administration',
  iRPourcentage: 9687.99,
  moelle: 'groin groin tandis que',
  dateMoelle: dayjs('2025-05-12'),
  resultatMoelle: 'vaste',
};

export const sampleWithNewData: NewCytogenetique = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
