import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMembre } from '../membre.model';
import { MembreService } from '../service/membre.service';

const membreResolve = (route: ActivatedRouteSnapshot): Observable<null | IMembre> => {
  const id = route.params.id;
  if (id) {
    return inject(MembreService)
      .find(id)
      .pipe(
        mergeMap((membre: HttpResponse<IMembre>) => {
          if (membre.body) {
            return of(membre.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default membreResolve;
