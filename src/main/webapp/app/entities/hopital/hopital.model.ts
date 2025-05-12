export interface IHopital {
  id: number;
  codeHopital?: number | null;
  nomHopital?: string | null;
}

export type NewHopital = Omit<IHopital, 'id'> & { id: null };
