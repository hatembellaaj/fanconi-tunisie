export interface IMedecin {
  id: number;
  cIN?: number | null;
  nomMedecin?: string | null;
  prenomMedecin?: string | null;
  grade?: string | null;
  typeMedecin?: string | null;
  gouvernoratM?: string | null;
  adresseM?: string | null;
  telM?: string | null;
  posteM?: string | null;
  faxM?: string | null;
  emailM?: string | null;
  hopital?: string | null;
  service?: string | null;
  image?: string | null;
  imageContentType?: string | null;
  login?: string | null;
  passwd?: string | null;
  uRL?: string | null;
}

export type NewMedecin = Omit<IMedecin, 'id'> & { id: null };
