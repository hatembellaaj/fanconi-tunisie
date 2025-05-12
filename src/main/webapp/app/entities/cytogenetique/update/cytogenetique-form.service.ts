import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { ICytogenetique, NewCytogenetique } from '../cytogenetique.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICytogenetique for edit and NewCytogenetiqueFormGroupInput for create.
 */
type CytogenetiqueFormGroupInput = ICytogenetique | PartialWithRequiredKeyOf<NewCytogenetique>;

type CytogenetiqueFormDefaults = Pick<NewCytogenetique, 'id'>;

type CytogenetiqueFormGroupContent = {
  id: FormControl<ICytogenetique['id'] | NewCytogenetique['id']>;
  nDossierPatient: FormControl<ICytogenetique['nDossierPatient']>;
  nEtudeCyto: FormControl<ICytogenetique['nEtudeCyto']>;
  lymphocytes: FormControl<ICytogenetique['lymphocytes']>;
  dateSang: FormControl<ICytogenetique['dateSang']>;
  laboratoire: FormControl<ICytogenetique['laboratoire']>;
  agentPontant: FormControl<ICytogenetique['agentPontant']>;
  instabilite: FormControl<ICytogenetique['instabilite']>;
  instabilitePourcentage: FormControl<ICytogenetique['instabilitePourcentage']>;
  iR: FormControl<ICytogenetique['iR']>;
  iRPourcentage: FormControl<ICytogenetique['iRPourcentage']>;
  moelle: FormControl<ICytogenetique['moelle']>;
  dateMoelle: FormControl<ICytogenetique['dateMoelle']>;
  resultatMoelle: FormControl<ICytogenetique['resultatMoelle']>;
};

export type CytogenetiqueFormGroup = FormGroup<CytogenetiqueFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CytogenetiqueFormService {
  createCytogenetiqueFormGroup(cytogenetique: CytogenetiqueFormGroupInput = { id: null }): CytogenetiqueFormGroup {
    const cytogenetiqueRawValue = {
      ...this.getFormDefaults(),
      ...cytogenetique,
    };
    return new FormGroup<CytogenetiqueFormGroupContent>({
      id: new FormControl(
        { value: cytogenetiqueRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nDossierPatient: new FormControl(cytogenetiqueRawValue.nDossierPatient),
      nEtudeCyto: new FormControl(cytogenetiqueRawValue.nEtudeCyto),
      lymphocytes: new FormControl(cytogenetiqueRawValue.lymphocytes),
      dateSang: new FormControl(cytogenetiqueRawValue.dateSang),
      laboratoire: new FormControl(cytogenetiqueRawValue.laboratoire),
      agentPontant: new FormControl(cytogenetiqueRawValue.agentPontant),
      instabilite: new FormControl(cytogenetiqueRawValue.instabilite),
      instabilitePourcentage: new FormControl(cytogenetiqueRawValue.instabilitePourcentage),
      iR: new FormControl(cytogenetiqueRawValue.iR),
      iRPourcentage: new FormControl(cytogenetiqueRawValue.iRPourcentage),
      moelle: new FormControl(cytogenetiqueRawValue.moelle),
      dateMoelle: new FormControl(cytogenetiqueRawValue.dateMoelle),
      resultatMoelle: new FormControl(cytogenetiqueRawValue.resultatMoelle),
    });
  }

  getCytogenetique(form: CytogenetiqueFormGroup): ICytogenetique | NewCytogenetique {
    return form.getRawValue() as ICytogenetique | NewCytogenetique;
  }

  resetForm(form: CytogenetiqueFormGroup, cytogenetique: CytogenetiqueFormGroupInput): void {
    const cytogenetiqueRawValue = { ...this.getFormDefaults(), ...cytogenetique };
    form.reset(
      {
        ...cytogenetiqueRawValue,
        id: { value: cytogenetiqueRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CytogenetiqueFormDefaults {
    return {
      id: null,
    };
  }
}
