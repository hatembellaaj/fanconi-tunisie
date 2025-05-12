import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAndrogene, NewAndrogene } from '../androgene.model';

export type PartialUpdateAndrogene = Partial<IAndrogene> & Pick<IAndrogene, 'id'>;

export type EntityResponseType = HttpResponse<IAndrogene>;
export type EntityArrayResponseType = HttpResponse<IAndrogene[]>;

@Injectable({ providedIn: 'root' })
export class AndrogeneService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/androgenes');

  create(androgene: NewAndrogene): Observable<EntityResponseType> {
    return this.http.post<IAndrogene>(this.resourceUrl, androgene, { observe: 'response' });
  }

  update(androgene: IAndrogene): Observable<EntityResponseType> {
    return this.http.put<IAndrogene>(`${this.resourceUrl}/${this.getAndrogeneIdentifier(androgene)}`, androgene, { observe: 'response' });
  }

  partialUpdate(androgene: PartialUpdateAndrogene): Observable<EntityResponseType> {
    return this.http.patch<IAndrogene>(`${this.resourceUrl}/${this.getAndrogeneIdentifier(androgene)}`, androgene, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAndrogene>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAndrogene[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAndrogeneIdentifier(androgene: Pick<IAndrogene, 'id'>): number {
    return androgene.id;
  }

  compareAndrogene(o1: Pick<IAndrogene, 'id'> | null, o2: Pick<IAndrogene, 'id'> | null): boolean {
    return o1 && o2 ? this.getAndrogeneIdentifier(o1) === this.getAndrogeneIdentifier(o2) : o1 === o2;
  }

  addAndrogeneToCollectionIfMissing<Type extends Pick<IAndrogene, 'id'>>(
    androgeneCollection: Type[],
    ...androgenesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const androgenes: Type[] = androgenesToCheck.filter(isPresent);
    if (androgenes.length > 0) {
      const androgeneCollectionIdentifiers = androgeneCollection.map(androgeneItem => this.getAndrogeneIdentifier(androgeneItem));
      const androgenesToAdd = androgenes.filter(androgeneItem => {
        const androgeneIdentifier = this.getAndrogeneIdentifier(androgeneItem);
        if (androgeneCollectionIdentifiers.includes(androgeneIdentifier)) {
          return false;
        }
        androgeneCollectionIdentifiers.push(androgeneIdentifier);
        return true;
      });
      return [...androgenesToAdd, ...androgeneCollection];
    }
    return androgeneCollection;
  }
}
