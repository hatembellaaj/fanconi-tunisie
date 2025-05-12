import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMedecin } from '../medecin.model';
import { MedecinService } from '../service/medecin.service';

const medecinResolve = (route: ActivatedRouteSnapshot): Observable<null | IMedecin> => {
  const id = route.params.id;
  if (id) {
    return inject(MedecinService)
      .find(id)
      .pipe(
        mergeMap((medecin: HttpResponse<IMedecin>) => {
          if (medecin.body) {
            return of(medecin.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default medecinResolve;
