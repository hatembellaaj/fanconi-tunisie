import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICytogenetique, NewCytogenetique } from '../cytogenetique.model';

export type PartialUpdateCytogenetique = Partial<ICytogenetique> & Pick<ICytogenetique, 'id'>;

type RestOf<T extends ICytogenetique | NewCytogenetique> = Omit<T, 'dateSang' | 'dateMoelle'> & {
  dateSang?: string | null;
  dateMoelle?: string | null;
};

export type RestCytogenetique = RestOf<ICytogenetique>;

export type NewRestCytogenetique = RestOf<NewCytogenetique>;

export type PartialUpdateRestCytogenetique = RestOf<PartialUpdateCytogenetique>;

export type EntityResponseType = HttpResponse<ICytogenetique>;
export type EntityArrayResponseType = HttpResponse<ICytogenetique[]>;

@Injectable({ providedIn: 'root' })
export class CytogenetiqueService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/cytogenetiques');

  create(cytogenetique: NewCytogenetique): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cytogenetique);
    return this.http
      .post<RestCytogenetique>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(cytogenetique: ICytogenetique): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cytogenetique);
    return this.http
      .put<RestCytogenetique>(`${this.resourceUrl}/${this.getCytogenetiqueIdentifier(cytogenetique)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(cytogenetique: PartialUpdateCytogenetique): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cytogenetique);
    return this.http
      .patch<RestCytogenetique>(`${this.resourceUrl}/${this.getCytogenetiqueIdentifier(cytogenetique)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestCytogenetique>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCytogenetique[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCytogenetiqueIdentifier(cytogenetique: Pick<ICytogenetique, 'id'>): number {
    return cytogenetique.id;
  }

  compareCytogenetique(o1: Pick<ICytogenetique, 'id'> | null, o2: Pick<ICytogenetique, 'id'> | null): boolean {
    return o1 && o2 ? this.getCytogenetiqueIdentifier(o1) === this.getCytogenetiqueIdentifier(o2) : o1 === o2;
  }

  addCytogenetiqueToCollectionIfMissing<Type extends Pick<ICytogenetique, 'id'>>(
    cytogenetiqueCollection: Type[],
    ...cytogenetiquesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const cytogenetiques: Type[] = cytogenetiquesToCheck.filter(isPresent);
    if (cytogenetiques.length > 0) {
      const cytogenetiqueCollectionIdentifiers = cytogenetiqueCollection.map(cytogenetiqueItem =>
        this.getCytogenetiqueIdentifier(cytogenetiqueItem),
      );
      const cytogenetiquesToAdd = cytogenetiques.filter(cytogenetiqueItem => {
        const cytogenetiqueIdentifier = this.getCytogenetiqueIdentifier(cytogenetiqueItem);
        if (cytogenetiqueCollectionIdentifiers.includes(cytogenetiqueIdentifier)) {
          return false;
        }
        cytogenetiqueCollectionIdentifiers.push(cytogenetiqueIdentifier);
        return true;
      });
      return [...cytogenetiquesToAdd, ...cytogenetiqueCollection];
    }
    return cytogenetiqueCollection;
  }

  protected convertDateFromClient<T extends ICytogenetique | NewCytogenetique | PartialUpdateCytogenetique>(cytogenetique: T): RestOf<T> {
    return {
      ...cytogenetique,
      dateSang: cytogenetique.dateSang?.format(DATE_FORMAT) ?? null,
      dateMoelle: cytogenetique.dateMoelle?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restCytogenetique: RestCytogenetique): ICytogenetique {
    return {
      ...restCytogenetique,
      dateSang: restCytogenetique.dateSang ? dayjs(restCytogenetique.dateSang) : undefined,
      dateMoelle: restCytogenetique.dateMoelle ? dayjs(restCytogenetique.dateMoelle) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCytogenetique>): HttpResponse<ICytogenetique> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCytogenetique[]>): HttpResponse<ICytogenetique[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
