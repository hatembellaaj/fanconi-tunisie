import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICousin, NewCousin } from '../cousin.model';

export type PartialUpdateCousin = Partial<ICousin> & Pick<ICousin, 'id'>;

export type EntityResponseType = HttpResponse<ICousin>;
export type EntityArrayResponseType = HttpResponse<ICousin[]>;

@Injectable({ providedIn: 'root' })
export class CousinService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/cousins');

  create(cousin: NewCousin): Observable<EntityResponseType> {
    return this.http.post<ICousin>(this.resourceUrl, cousin, { observe: 'response' });
  }

  update(cousin: ICousin): Observable<EntityResponseType> {
    return this.http.put<ICousin>(`${this.resourceUrl}/${this.getCousinIdentifier(cousin)}`, cousin, { observe: 'response' });
  }

  partialUpdate(cousin: PartialUpdateCousin): Observable<EntityResponseType> {
    return this.http.patch<ICousin>(`${this.resourceUrl}/${this.getCousinIdentifier(cousin)}`, cousin, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICousin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICousin[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCousinIdentifier(cousin: Pick<ICousin, 'id'>): number {
    return cousin.id;
  }

  compareCousin(o1: Pick<ICousin, 'id'> | null, o2: Pick<ICousin, 'id'> | null): boolean {
    return o1 && o2 ? this.getCousinIdentifier(o1) === this.getCousinIdentifier(o2) : o1 === o2;
  }

  addCousinToCollectionIfMissing<Type extends Pick<ICousin, 'id'>>(
    cousinCollection: Type[],
    ...cousinsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const cousins: Type[] = cousinsToCheck.filter(isPresent);
    if (cousins.length > 0) {
      const cousinCollectionIdentifiers = cousinCollection.map(cousinItem => this.getCousinIdentifier(cousinItem));
      const cousinsToAdd = cousins.filter(cousinItem => {
        const cousinIdentifier = this.getCousinIdentifier(cousinItem);
        if (cousinCollectionIdentifiers.includes(cousinIdentifier)) {
          return false;
        }
        cousinCollectionIdentifiers.push(cousinIdentifier);
        return true;
      });
      return [...cousinsToAdd, ...cousinCollection];
    }
    return cousinCollection;
  }
}
