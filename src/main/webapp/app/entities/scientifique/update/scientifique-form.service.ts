import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IScientifique, NewScientifique } from '../scientifique.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IScientifique for edit and NewScientifiqueFormGroupInput for create.
 */
type ScientifiqueFormGroupInput = IScientifique | PartialWithRequiredKeyOf<NewScientifique>;

type ScientifiqueFormDefaults = Pick<NewScientifique, 'id'>;

type ScientifiqueFormGroupContent = {
  id: FormControl<IScientifique['id'] | NewScientifique['id']>;
  codeSC: FormControl<IScientifique['codeSC']>;
  nomSC: FormControl<IScientifique['nomSC']>;
  prenomSC: FormControl<IScientifique['prenomSC']>;
  serviceSC: FormControl<IScientifique['serviceSC']>;
  centreSC: FormControl<IScientifique['centreSC']>;
  adresseSC: FormControl<IScientifique['adresseSC']>;
  telSC: FormControl<IScientifique['telSC']>;
  emailSC: FormControl<IScientifique['emailSC']>;
  photoSC: FormControl<IScientifique['photoSC']>;
  photoSCContentType: FormControl<IScientifique['photoSCContentType']>;
  typeSC: FormControl<IScientifique['typeSC']>;
  loginSC: FormControl<IScientifique['loginSC']>;
  passwdSC: FormControl<IScientifique['passwdSC']>;
  uRL: FormControl<IScientifique['uRL']>;
};

export type ScientifiqueFormGroup = FormGroup<ScientifiqueFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ScientifiqueFormService {
  createScientifiqueFormGroup(scientifique: ScientifiqueFormGroupInput = { id: null }): ScientifiqueFormGroup {
    const scientifiqueRawValue = {
      ...this.getFormDefaults(),
      ...scientifique,
    };
    return new FormGroup<ScientifiqueFormGroupContent>({
      id: new FormControl(
        { value: scientifiqueRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      codeSC: new FormControl(scientifiqueRawValue.codeSC),
      nomSC: new FormControl(scientifiqueRawValue.nomSC),
      prenomSC: new FormControl(scientifiqueRawValue.prenomSC),
      serviceSC: new FormControl(scientifiqueRawValue.serviceSC),
      centreSC: new FormControl(scientifiqueRawValue.centreSC),
      adresseSC: new FormControl(scientifiqueRawValue.adresseSC),
      telSC: new FormControl(scientifiqueRawValue.telSC),
      emailSC: new FormControl(scientifiqueRawValue.emailSC),
      photoSC: new FormControl(scientifiqueRawValue.photoSC),
      photoSCContentType: new FormControl(scientifiqueRawValue.photoSCContentType),
      typeSC: new FormControl(scientifiqueRawValue.typeSC),
      loginSC: new FormControl(scientifiqueRawValue.loginSC),
      passwdSC: new FormControl(scientifiqueRawValue.passwdSC),
      uRL: new FormControl(scientifiqueRawValue.uRL),
    });
  }

  getScientifique(form: ScientifiqueFormGroup): IScientifique | NewScientifique {
    return form.getRawValue() as IScientifique | NewScientifique;
  }

  resetForm(form: ScientifiqueFormGroup, scientifique: ScientifiqueFormGroupInput): void {
    const scientifiqueRawValue = { ...this.getFormDefaults(), ...scientifique };
    form.reset(
      {
        ...scientifiqueRawValue,
        id: { value: scientifiqueRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ScientifiqueFormDefaults {
    return {
      id: null,
    };
  }
}
