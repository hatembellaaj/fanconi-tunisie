import { IHopital, NewHopital } from './hopital.model';

export const sampleWithRequiredData: IHopital = {
  id: 2469,
};

export const sampleWithPartialData: IHopital = {
  id: 16069,
};

export const sampleWithFullData: IHopital = {
  id: 10929,
  codeHopital: 13001,
  nomHopital: 'guère parlementaire',
};

export const sampleWithNewData: NewHopital = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
