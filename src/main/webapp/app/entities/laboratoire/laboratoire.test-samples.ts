import { ILaboratoire, NewLaboratoire } from './laboratoire.model';

export const sampleWithRequiredData: ILaboratoire = {
  id: 19008,
};

export const sampleWithPartialData: ILaboratoire = {
  id: 2948,
};

export const sampleWithFullData: ILaboratoire = {
  id: 18364,
  nomLaboratoire: 'toc désagréable bien que',
};

export const sampleWithNewData: NewLaboratoire = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
