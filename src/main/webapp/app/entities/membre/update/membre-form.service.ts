import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IMembre, NewMembre } from '../membre.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMembre for edit and NewMembreFormGroupInput for create.
 */
type MembreFormGroupInput = IMembre | PartialWithRequiredKeyOf<NewMembre>;

type MembreFormDefaults = Pick<NewMembre, 'id'>;

type MembreFormGroupContent = {
  id: FormControl<IMembre['id'] | NewMembre['id']>;
  nDossierM: FormControl<IMembre['nDossierM']>;
  idMembre: FormControl<IMembre['idMembre']>;
  prenomM: FormControl<IMembre['prenomM']>;
  lienParente: FormControl<IMembre['lienParente']>;
  typeCancerM: FormControl<IMembre['typeCancerM']>;
  ageDecouverteM: FormControl<IMembre['ageDecouverteM']>;
};

export type MembreFormGroup = FormGroup<MembreFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MembreFormService {
  createMembreFormGroup(membre: MembreFormGroupInput = { id: null }): MembreFormGroup {
    const membreRawValue = {
      ...this.getFormDefaults(),
      ...membre,
    };
    return new FormGroup<MembreFormGroupContent>({
      id: new FormControl(
        { value: membreRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nDossierM: new FormControl(membreRawValue.nDossierM),
      idMembre: new FormControl(membreRawValue.idMembre),
      prenomM: new FormControl(membreRawValue.prenomM),
      lienParente: new FormControl(membreRawValue.lienParente),
      typeCancerM: new FormControl(membreRawValue.typeCancerM),
      ageDecouverteM: new FormControl(membreRawValue.ageDecouverteM),
    });
  }

  getMembre(form: MembreFormGroup): IMembre | NewMembre {
    return form.getRawValue() as IMembre | NewMembre;
  }

  resetForm(form: MembreFormGroup, membre: MembreFormGroupInput): void {
    const membreRawValue = { ...this.getFormDefaults(), ...membre };
    form.reset(
      {
        ...membreRawValue,
        id: { value: membreRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): MembreFormDefaults {
    return {
      id: null,
    };
  }
}
