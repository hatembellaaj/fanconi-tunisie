import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 9935,
  login: 'htlGu',
};

export const sampleWithPartialData: IUser = {
  id: 28921,
  login: 'rMsmj',
};

export const sampleWithFullData: IUser = {
  id: 17423,
  login: 'k@aP\\$EeKP0\\!I9\\@YbY210\\JfuhuN',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
