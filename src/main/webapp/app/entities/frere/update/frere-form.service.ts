import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IFrere, NewFrere } from '../frere.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFrere for edit and NewFrereFormGroupInput for create.
 */
type FrereFormGroupInput = IFrere | PartialWithRequiredKeyOf<NewFrere>;

type FrereFormDefaults = Pick<NewFrere, 'id'>;

type FrereFormGroupContent = {
  id: FormControl<IFrere['id'] | NewFrere['id']>;
  nDossierF: FormControl<IFrere['nDossierF']>;
  idFrere: FormControl<IFrere['idFrere']>;
  prenomFrere: FormControl<IFrere['prenomFrere']>;
  atteint: FormControl<IFrere['atteint']>;
  placefratrie: FormControl<IFrere['placefratrie']>;
  sexeF: FormControl<IFrere['sexeF']>;
  decedes: FormControl<IFrere['decedes']>;
  age: FormControl<IFrere['age']>;
};

export type FrereFormGroup = FormGroup<FrereFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FrereFormService {
  createFrereFormGroup(frere: FrereFormGroupInput = { id: null }): FrereFormGroup {
    const frereRawValue = {
      ...this.getFormDefaults(),
      ...frere,
    };
    return new FormGroup<FrereFormGroupContent>({
      id: new FormControl(
        { value: frereRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nDossierF: new FormControl(frereRawValue.nDossierF),
      idFrere: new FormControl(frereRawValue.idFrere),
      prenomFrere: new FormControl(frereRawValue.prenomFrere),
      atteint: new FormControl(frereRawValue.atteint),
      placefratrie: new FormControl(frereRawValue.placefratrie),
      sexeF: new FormControl(frereRawValue.sexeF),
      decedes: new FormControl(frereRawValue.decedes),
      age: new FormControl(frereRawValue.age),
    });
  }

  getFrere(form: FrereFormGroup): IFrere | NewFrere {
    return form.getRawValue() as IFrere | NewFrere;
  }

  resetForm(form: FrereFormGroup, frere: FrereFormGroupInput): void {
    const frereRawValue = { ...this.getFormDefaults(), ...frere };
    form.reset(
      {
        ...frereRawValue,
        id: { value: frereRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FrereFormDefaults {
    return {
      id: null,
    };
  }
}
