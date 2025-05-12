import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICytogenetique } from '../cytogenetique.model';
import { CytogenetiqueService } from '../service/cytogenetique.service';

const cytogenetiqueResolve = (route: ActivatedRouteSnapshot): Observable<null | ICytogenetique> => {
  const id = route.params.id;
  if (id) {
    return inject(CytogenetiqueService)
      .find(id)
      .pipe(
        mergeMap((cytogenetique: HttpResponse<ICytogenetique>) => {
          if (cytogenetique.body) {
            return of(cytogenetique.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default cytogenetiqueResolve;
