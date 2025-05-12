import dayjs from 'dayjs/esm';

export interface ICytogenetique {
  id: number;
  nDossierPatient?: string | null;
  nEtudeCyto?: number | null;
  lymphocytes?: string | null;
  dateSang?: dayjs.Dayjs | null;
  laboratoire?: string | null;
  agentPontant?: string | null;
  instabilite?: string | null;
  instabilitePourcentage?: number | null;
  iR?: string | null;
  iRPourcentage?: number | null;
  moelle?: string | null;
  dateMoelle?: dayjs.Dayjs | null;
  resultatMoelle?: string | null;
}

export type NewCytogenetique = Omit<ICytogenetique, 'id'> & { id: null };
