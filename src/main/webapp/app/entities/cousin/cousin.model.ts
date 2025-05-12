export interface ICousin {
  id: number;
  ndossierC?: string | null;
  idCousin?: number | null;
  prenomCousin?: string | null;
  placeCousin?: string | null;
  sexeC?: string | null;
}

export type NewCousin = Omit<ICousin, 'id'> & { id: null };
