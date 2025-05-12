import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFrere, NewFrere } from '../frere.model';

export type PartialUpdateFrere = Partial<IFrere> & Pick<IFrere, 'id'>;

export type EntityResponseType = HttpResponse<IFrere>;
export type EntityArrayResponseType = HttpResponse<IFrere[]>;

@Injectable({ providedIn: 'root' })
export class FrereService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/freres');

  create(frere: NewFrere): Observable<EntityResponseType> {
    return this.http.post<IFrere>(this.resourceUrl, frere, { observe: 'response' });
  }

  update(frere: IFrere): Observable<EntityResponseType> {
    return this.http.put<IFrere>(`${this.resourceUrl}/${this.getFrereIdentifier(frere)}`, frere, { observe: 'response' });
  }

  partialUpdate(frere: PartialUpdateFrere): Observable<EntityResponseType> {
    return this.http.patch<IFrere>(`${this.resourceUrl}/${this.getFrereIdentifier(frere)}`, frere, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFrere>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFrere[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFrereIdentifier(frere: Pick<IFrere, 'id'>): number {
    return frere.id;
  }

  compareFrere(o1: Pick<IFrere, 'id'> | null, o2: Pick<IFrere, 'id'> | null): boolean {
    return o1 && o2 ? this.getFrereIdentifier(o1) === this.getFrereIdentifier(o2) : o1 === o2;
  }

  addFrereToCollectionIfMissing<Type extends Pick<IFrere, 'id'>>(
    frereCollection: Type[],
    ...freresToCheck: (Type | null | undefined)[]
  ): Type[] {
    const freres: Type[] = freresToCheck.filter(isPresent);
    if (freres.length > 0) {
      const frereCollectionIdentifiers = frereCollection.map(frereItem => this.getFrereIdentifier(frereItem));
      const freresToAdd = freres.filter(frereItem => {
        const frereIdentifier = this.getFrereIdentifier(frereItem);
        if (frereCollectionIdentifiers.includes(frereIdentifier)) {
          return false;
        }
        frereCollectionIdentifiers.push(frereIdentifier);
        return true;
      });
      return [...freresToAdd, ...frereCollection];
    }
    return frereCollection;
  }
}
