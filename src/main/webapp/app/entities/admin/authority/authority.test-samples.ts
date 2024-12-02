import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'd6e91e5e-13a0-4ee8-8a6e-c2c01d9af43c',
};

export const sampleWithPartialData: IAuthority = {
  name: '7c8c639b-6f11-47f3-8d61-fba79bfb3885',
};

export const sampleWithFullData: IAuthority = {
  name: 'dd3883f1-7ad9-415f-96cf-982490400535',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
