import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFrere } from '../frere.model';
import { FrereService } from '../service/frere.service';

const frereResolve = (route: ActivatedRouteSnapshot): Observable<null | IFrere> => {
  const id = route.params.id;
  if (id) {
    return inject(FrereService)
      .find(id)
      .pipe(
        mergeMap((frere: HttpResponse<IFrere>) => {
          if (frere.body) {
            return of(frere.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default frereResolve;
