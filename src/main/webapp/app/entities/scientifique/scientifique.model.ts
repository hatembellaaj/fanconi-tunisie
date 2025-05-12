export interface IScientifique {
  id: number;
  codeSC?: number | null;
  nomSC?: string | null;
  prenomSC?: string | null;
  serviceSC?: string | null;
  centreSC?: string | null;
  adresseSC?: string | null;
  telSC?: string | null;
  emailSC?: string | null;
  photoSC?: string | null;
  photoSCContentType?: string | null;
  typeSC?: string | null;
  loginSC?: string | null;
  passwdSC?: string | null;
  uRL?: string | null;
}

export type NewScientifique = Omit<IScientifique, 'id'> & { id: null };
