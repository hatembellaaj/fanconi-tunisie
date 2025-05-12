import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IScientifique } from '../scientifique.model';
import { ScientifiqueService } from '../service/scientifique.service';

const scientifiqueResolve = (route: ActivatedRouteSnapshot): Observable<null | IScientifique> => {
  const id = route.params.id;
  if (id) {
    return inject(ScientifiqueService)
      .find(id)
      .pipe(
        mergeMap((scientifique: HttpResponse<IScientifique>) => {
          if (scientifique.body) {
            return of(scientifique.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default scientifiqueResolve;
