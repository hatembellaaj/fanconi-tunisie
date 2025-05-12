import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IAndrogene, NewAndrogene } from '../androgene.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAndrogene for edit and NewAndrogeneFormGroupInput for create.
 */
type AndrogeneFormGroupInput = IAndrogene | PartialWithRequiredKeyOf<NewAndrogene>;

type AndrogeneFormDefaults = Pick<NewAndrogene, 'id'>;

type AndrogeneFormGroupContent = {
  id: FormControl<IAndrogene['id'] | NewAndrogene['id']>;
  nDossierPa: FormControl<IAndrogene['nDossierPa']>;
  mois: FormControl<IAndrogene['mois']>;
  reponse: FormControl<IAndrogene['reponse']>;
};

export type AndrogeneFormGroup = FormGroup<AndrogeneFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AndrogeneFormService {
  createAndrogeneFormGroup(androgene: AndrogeneFormGroupInput = { id: null }): AndrogeneFormGroup {
    const androgeneRawValue = {
      ...this.getFormDefaults(),
      ...androgene,
    };
    return new FormGroup<AndrogeneFormGroupContent>({
      id: new FormControl(
        { value: androgeneRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nDossierPa: new FormControl(androgeneRawValue.nDossierPa),
      mois: new FormControl(androgeneRawValue.mois),
      reponse: new FormControl(androgeneRawValue.reponse),
    });
  }

  getAndrogene(form: AndrogeneFormGroup): IAndrogene | NewAndrogene {
    return form.getRawValue() as IAndrogene | NewAndrogene;
  }

  resetForm(form: AndrogeneFormGroup, androgene: AndrogeneFormGroupInput): void {
    const androgeneRawValue = { ...this.getFormDefaults(), ...androgene };
    form.reset(
      {
        ...androgeneRawValue,
        id: { value: androgeneRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AndrogeneFormDefaults {
    return {
      id: null,
    };
  }
}
