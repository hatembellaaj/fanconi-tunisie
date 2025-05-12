export interface IFrere {
  id: number;
  nDossierF?: string | null;
  idFrere?: number | null;
  prenomFrere?: string | null;
  atteint?: string | null;
  placefratrie?: number | null;
  sexeF?: string | null;
  decedes?: string | null;
  age?: number | null;
}

export type NewFrere = Omit<IFrere, 'id'> & { id: null };
