import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { ICousin, NewCousin } from '../cousin.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICousin for edit and NewCousinFormGroupInput for create.
 */
type CousinFormGroupInput = ICousin | PartialWithRequiredKeyOf<NewCousin>;

type CousinFormDefaults = Pick<NewCousin, 'id'>;

type CousinFormGroupContent = {
  id: FormControl<ICousin['id'] | NewCousin['id']>;
  ndossierC: FormControl<ICousin['ndossierC']>;
  idCousin: FormControl<ICousin['idCousin']>;
  prenomCousin: FormControl<ICousin['prenomCousin']>;
  placeCousin: FormControl<ICousin['placeCousin']>;
  sexeC: FormControl<ICousin['sexeC']>;
};

export type CousinFormGroup = FormGroup<CousinFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CousinFormService {
  createCousinFormGroup(cousin: CousinFormGroupInput = { id: null }): CousinFormGroup {
    const cousinRawValue = {
      ...this.getFormDefaults(),
      ...cousin,
    };
    return new FormGroup<CousinFormGroupContent>({
      id: new FormControl(
        { value: cousinRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      ndossierC: new FormControl(cousinRawValue.ndossierC),
      idCousin: new FormControl(cousinRawValue.idCousin),
      prenomCousin: new FormControl(cousinRawValue.prenomCousin),
      placeCousin: new FormControl(cousinRawValue.placeCousin),
      sexeC: new FormControl(cousinRawValue.sexeC),
    });
  }

  getCousin(form: CousinFormGroup): ICousin | NewCousin {
    return form.getRawValue() as ICousin | NewCousin;
  }

  resetForm(form: CousinFormGroup, cousin: CousinFormGroupInput): void {
    const cousinRawValue = { ...this.getFormDefaults(), ...cousin };
    form.reset(
      {
        ...cousinRawValue,
        id: { value: cousinRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CousinFormDefaults {
    return {
      id: null,
    };
  }
}
