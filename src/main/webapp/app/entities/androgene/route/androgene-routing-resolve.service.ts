import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAndrogene } from '../androgene.model';
import { AndrogeneService } from '../service/androgene.service';

const androgeneResolve = (route: ActivatedRouteSnapshot): Observable<null | IAndrogene> => {
  const id = route.params.id;
  if (id) {
    return inject(AndrogeneService)
      .find(id)
      .pipe(
        mergeMap((androgene: HttpResponse<IAndrogene>) => {
          if (androgene.body) {
            return of(androgene.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default androgeneResolve;
