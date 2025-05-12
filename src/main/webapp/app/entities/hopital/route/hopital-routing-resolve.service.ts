import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IHopital } from '../hopital.model';
import { HopitalService } from '../service/hopital.service';

const hopitalResolve = (route: ActivatedRouteSnapshot): Observable<null | IHopital> => {
  const id = route.params.id;
  if (id) {
    return inject(HopitalService)
      .find(id)
      .pipe(
        mergeMap((hopital: HttpResponse<IHopital>) => {
          if (hopital.body) {
            return of(hopital.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default hopitalResolve;
