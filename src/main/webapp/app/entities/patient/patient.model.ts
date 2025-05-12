export interface IPatient {
  id: number;
  ndossierP?: string | null;
  nom?: string | null;
  prenom?: string | null;
  dateNaissance?: string | null;
  lieuNaissance?: string | null;
  sexe?: string | null;
  gouvernorat?: string | null;
  adresse?: string | null;
  reperes?: string | null;
  tel?: string | null;
  prenomPere?: string | null;
  nomMere?: string | null;
  prenomMere?: string | null;
  nomGMP?: string | null;
  nomGMM?: string | null;
  age?: number | null;
}

export type NewPatient = Omit<IPatient, 'id'> & { id: null };
