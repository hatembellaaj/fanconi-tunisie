export interface ILaboratoire {
  id: number;
  nomLaboratoire?: string | null;
}

export type NewLaboratoire = Omit<ILaboratoire, 'id'> & { id: null };
