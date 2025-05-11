import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'a80dc1f8-6666-4cba-b329-d54f22c68ee9',
};

export const sampleWithPartialData: IAuthority = {
  name: 'f6fd9b8f-02a4-4dd4-8778-668c5f6b43ec',
};

export const sampleWithFullData: IAuthority = {
  name: '93f064e5-01c9-44a0-8b20-75a72b789998',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
