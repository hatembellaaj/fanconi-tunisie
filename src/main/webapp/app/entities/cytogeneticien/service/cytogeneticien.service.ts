import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICytogeneticien, NewCytogeneticien } from '../cytogeneticien.model';

export type PartialUpdateCytogeneticien = Partial<ICytogeneticien> & Pick<ICytogeneticien, 'id'>;

export type EntityResponseType = HttpResponse<ICytogeneticien>;
export type EntityArrayResponseType = HttpResponse<ICytogeneticien[]>;

@Injectable({ providedIn: 'root' })
export class CytogeneticienService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/cytogeneticiens');

  create(cytogeneticien: NewCytogeneticien): Observable<EntityResponseType> {
    return this.http.post<ICytogeneticien>(this.resourceUrl, cytogeneticien, { observe: 'response' });
  }

  update(cytogeneticien: ICytogeneticien): Observable<EntityResponseType> {
    return this.http.put<ICytogeneticien>(`${this.resourceUrl}/${this.getCytogeneticienIdentifier(cytogeneticien)}`, cytogeneticien, {
      observe: 'response',
    });
  }

  partialUpdate(cytogeneticien: PartialUpdateCytogeneticien): Observable<EntityResponseType> {
    return this.http.patch<ICytogeneticien>(`${this.resourceUrl}/${this.getCytogeneticienIdentifier(cytogeneticien)}`, cytogeneticien, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICytogeneticien>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICytogeneticien[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCytogeneticienIdentifier(cytogeneticien: Pick<ICytogeneticien, 'id'>): number {
    return cytogeneticien.id;
  }

  compareCytogeneticien(o1: Pick<ICytogeneticien, 'id'> | null, o2: Pick<ICytogeneticien, 'id'> | null): boolean {
    return o1 && o2 ? this.getCytogeneticienIdentifier(o1) === this.getCytogeneticienIdentifier(o2) : o1 === o2;
  }

  addCytogeneticienToCollectionIfMissing<Type extends Pick<ICytogeneticien, 'id'>>(
    cytogeneticienCollection: Type[],
    ...cytogeneticiensToCheck: (Type | null | undefined)[]
  ): Type[] {
    const cytogeneticiens: Type[] = cytogeneticiensToCheck.filter(isPresent);
    if (cytogeneticiens.length > 0) {
      const cytogeneticienCollectionIdentifiers = cytogeneticienCollection.map(cytogeneticienItem =>
        this.getCytogeneticienIdentifier(cytogeneticienItem),
      );
      const cytogeneticiensToAdd = cytogeneticiens.filter(cytogeneticienItem => {
        const cytogeneticienIdentifier = this.getCytogeneticienIdentifier(cytogeneticienItem);
        if (cytogeneticienCollectionIdentifiers.includes(cytogeneticienIdentifier)) {
          return false;
        }
        cytogeneticienCollectionIdentifiers.push(cytogeneticienIdentifier);
        return true;
      });
      return [...cytogeneticiensToAdd, ...cytogeneticienCollection];
    }
    return cytogeneticienCollection;
  }
}
