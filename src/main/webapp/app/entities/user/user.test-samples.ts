import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 22513,
  login: 'lui',
};

export const sampleWithPartialData: IUser = {
  id: 3241,
  login: 'G@0\\BCxTJx\\uXw762I\\+m9r1IA\\[Lsl',
};

export const sampleWithFullData: IUser = {
  id: 12128,
  login: '8f-eW',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
