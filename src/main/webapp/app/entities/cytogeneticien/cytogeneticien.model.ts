export interface ICytogeneticien {
  id: number;
  code?: number | null;
  nom?: string | null;
  prenom?: string | null;
  service?: string | null;
  etab?: string | null;
  adresse?: string | null;
  tel?: string | null;
  poste?: string | null;
  fax?: string | null;
  email?: string | null;
  photo?: string | null;
  photoContentType?: string | null;
  type?: string | null;
  login?: string | null;
  passwd?: string | null;
  uRL?: string | null;
}

export type NewCytogeneticien = Omit<ICytogeneticien, 'id'> & { id: null };
