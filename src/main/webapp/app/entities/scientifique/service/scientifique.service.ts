import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IScientifique, NewScientifique } from '../scientifique.model';

export type PartialUpdateScientifique = Partial<IScientifique> & Pick<IScientifique, 'id'>;

export type EntityResponseType = HttpResponse<IScientifique>;
export type EntityArrayResponseType = HttpResponse<IScientifique[]>;

@Injectable({ providedIn: 'root' })
export class ScientifiqueService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/scientifiques');

  create(scientifique: NewScientifique): Observable<EntityResponseType> {
    return this.http.post<IScientifique>(this.resourceUrl, scientifique, { observe: 'response' });
  }

  update(scientifique: IScientifique): Observable<EntityResponseType> {
    return this.http.put<IScientifique>(`${this.resourceUrl}/${this.getScientifiqueIdentifier(scientifique)}`, scientifique, {
      observe: 'response',
    });
  }

  partialUpdate(scientifique: PartialUpdateScientifique): Observable<EntityResponseType> {
    return this.http.patch<IScientifique>(`${this.resourceUrl}/${this.getScientifiqueIdentifier(scientifique)}`, scientifique, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IScientifique>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IScientifique[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getScientifiqueIdentifier(scientifique: Pick<IScientifique, 'id'>): number {
    return scientifique.id;
  }

  compareScientifique(o1: Pick<IScientifique, 'id'> | null, o2: Pick<IScientifique, 'id'> | null): boolean {
    return o1 && o2 ? this.getScientifiqueIdentifier(o1) === this.getScientifiqueIdentifier(o2) : o1 === o2;
  }

  addScientifiqueToCollectionIfMissing<Type extends Pick<IScientifique, 'id'>>(
    scientifiqueCollection: Type[],
    ...scientifiquesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const scientifiques: Type[] = scientifiquesToCheck.filter(isPresent);
    if (scientifiques.length > 0) {
      const scientifiqueCollectionIdentifiers = scientifiqueCollection.map(scientifiqueItem =>
        this.getScientifiqueIdentifier(scientifiqueItem),
      );
      const scientifiquesToAdd = scientifiques.filter(scientifiqueItem => {
        const scientifiqueIdentifier = this.getScientifiqueIdentifier(scientifiqueItem);
        if (scientifiqueCollectionIdentifiers.includes(scientifiqueIdentifier)) {
          return false;
        }
        scientifiqueCollectionIdentifiers.push(scientifiqueIdentifier);
        return true;
      });
      return [...scientifiquesToAdd, ...scientifiqueCollection];
    }
    return scientifiqueCollection;
  }
}
