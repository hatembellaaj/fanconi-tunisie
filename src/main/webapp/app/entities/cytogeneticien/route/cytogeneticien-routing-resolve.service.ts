import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICytogeneticien } from '../cytogeneticien.model';
import { CytogeneticienService } from '../service/cytogeneticien.service';

const cytogeneticienResolve = (route: ActivatedRouteSnapshot): Observable<null | ICytogeneticien> => {
  const id = route.params.id;
  if (id) {
    return inject(CytogeneticienService)
      .find(id)
      .pipe(
        mergeMap((cytogeneticien: HttpResponse<ICytogeneticien>) => {
          if (cytogeneticien.body) {
            return of(cytogeneticien.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default cytogeneticienResolve;
