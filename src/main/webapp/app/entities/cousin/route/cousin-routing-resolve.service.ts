import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICousin } from '../cousin.model';
import { CousinService } from '../service/cousin.service';

const cousinResolve = (route: ActivatedRouteSnapshot): Observable<null | ICousin> => {
  const id = route.params.id;
  if (id) {
    return inject(CousinService)
      .find(id)
      .pipe(
        mergeMap((cousin: HttpResponse<ICousin>) => {
          if (cousin.body) {
            return of(cousin.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default cousinResolve;
