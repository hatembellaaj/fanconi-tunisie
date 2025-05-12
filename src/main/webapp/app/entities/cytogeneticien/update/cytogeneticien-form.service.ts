import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { ICytogeneticien, NewCytogeneticien } from '../cytogeneticien.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICytogeneticien for edit and NewCytogeneticienFormGroupInput for create.
 */
type CytogeneticienFormGroupInput = ICytogeneticien | PartialWithRequiredKeyOf<NewCytogeneticien>;

type CytogeneticienFormDefaults = Pick<NewCytogeneticien, 'id'>;

type CytogeneticienFormGroupContent = {
  id: FormControl<ICytogeneticien['id'] | NewCytogeneticien['id']>;
  code: FormControl<ICytogeneticien['code']>;
  nom: FormControl<ICytogeneticien['nom']>;
  prenom: FormControl<ICytogeneticien['prenom']>;
  service: FormControl<ICytogeneticien['service']>;
  etab: FormControl<ICytogeneticien['etab']>;
  adresse: FormControl<ICytogeneticien['adresse']>;
  tel: FormControl<ICytogeneticien['tel']>;
  poste: FormControl<ICytogeneticien['poste']>;
  fax: FormControl<ICytogeneticien['fax']>;
  email: FormControl<ICytogeneticien['email']>;
  photo: FormControl<ICytogeneticien['photo']>;
  photoContentType: FormControl<ICytogeneticien['photoContentType']>;
  type: FormControl<ICytogeneticien['type']>;
  login: FormControl<ICytogeneticien['login']>;
  passwd: FormControl<ICytogeneticien['passwd']>;
  uRL: FormControl<ICytogeneticien['uRL']>;
};

export type CytogeneticienFormGroup = FormGroup<CytogeneticienFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CytogeneticienFormService {
  createCytogeneticienFormGroup(cytogeneticien: CytogeneticienFormGroupInput = { id: null }): CytogeneticienFormGroup {
    const cytogeneticienRawValue = {
      ...this.getFormDefaults(),
      ...cytogeneticien,
    };
    return new FormGroup<CytogeneticienFormGroupContent>({
      id: new FormControl(
        { value: cytogeneticienRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      code: new FormControl(cytogeneticienRawValue.code),
      nom: new FormControl(cytogeneticienRawValue.nom),
      prenom: new FormControl(cytogeneticienRawValue.prenom),
      service: new FormControl(cytogeneticienRawValue.service),
      etab: new FormControl(cytogeneticienRawValue.etab),
      adresse: new FormControl(cytogeneticienRawValue.adresse),
      tel: new FormControl(cytogeneticienRawValue.tel),
      poste: new FormControl(cytogeneticienRawValue.poste),
      fax: new FormControl(cytogeneticienRawValue.fax),
      email: new FormControl(cytogeneticienRawValue.email),
      photo: new FormControl(cytogeneticienRawValue.photo),
      photoContentType: new FormControl(cytogeneticienRawValue.photoContentType),
      type: new FormControl(cytogeneticienRawValue.type),
      login: new FormControl(cytogeneticienRawValue.login),
      passwd: new FormControl(cytogeneticienRawValue.passwd),
      uRL: new FormControl(cytogeneticienRawValue.uRL),
    });
  }

  getCytogeneticien(form: CytogeneticienFormGroup): ICytogeneticien | NewCytogeneticien {
    return form.getRawValue() as ICytogeneticien | NewCytogeneticien;
  }

  resetForm(form: CytogeneticienFormGroup, cytogeneticien: CytogeneticienFormGroupInput): void {
    const cytogeneticienRawValue = { ...this.getFormDefaults(), ...cytogeneticien };
    form.reset(
      {
        ...cytogeneticienRawValue,
        id: { value: cytogeneticienRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CytogeneticienFormDefaults {
    return {
      id: null,
    };
  }
}
