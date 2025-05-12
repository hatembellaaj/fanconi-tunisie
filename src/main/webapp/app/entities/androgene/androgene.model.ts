export interface IAndrogene {
  id: number;
  nDossierPa?: string | null;
  mois?: number | null;
  reponse?: string | null;
}

export type NewAndrogene = Omit<IAndrogene, 'id'> & { id: null };
