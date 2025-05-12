export interface IMembre {
  id: number;
  nDossierM?: string | null;
  idMembre?: number | null;
  prenomM?: string | null;
  lienParente?: string | null;
  typeCancerM?: string | null;
  ageDecouverteM?: number | null;
}

export type NewMembre = Omit<IMembre, 'id'> & { id: null };
