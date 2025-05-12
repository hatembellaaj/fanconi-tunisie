import { IService, NewService } from './service.model';

export const sampleWithRequiredData: IService = {
  id: 31448,
};

export const sampleWithPartialData: IService = {
  id: 26610,
  codeService: 15149,
};

export const sampleWithFullData: IService = {
  id: 1485,
  codeService: 15578,
  nomService: "chef à l'encontre de patientèle",
};

export const sampleWithNewData: NewService = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
