export interface IService {
  id: number;
  codeService?: number | null;
  nomService?: string | null;
}

export type NewService = Omit<IService, 'id'> & { id: null };
