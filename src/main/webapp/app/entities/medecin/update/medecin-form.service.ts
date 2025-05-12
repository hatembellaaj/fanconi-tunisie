import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IMedecin, NewMedecin } from '../medecin.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMedecin for edit and NewMedecinFormGroupInput for create.
 */
type MedecinFormGroupInput = IMedecin | PartialWithRequiredKeyOf<NewMedecin>;

type MedecinFormDefaults = Pick<NewMedecin, 'id'>;

type MedecinFormGroupContent = {
  id: FormControl<IMedecin['id'] | NewMedecin['id']>;
  cIN: FormControl<IMedecin['cIN']>;
  nomMedecin: FormControl<IMedecin['nomMedecin']>;
  prenomMedecin: FormControl<IMedecin['prenomMedecin']>;
  grade: FormControl<IMedecin['grade']>;
  typeMedecin: FormControl<IMedecin['typeMedecin']>;
  gouvernoratM: FormControl<IMedecin['gouvernoratM']>;
  adresseM: FormControl<IMedecin['adresseM']>;
  telM: FormControl<IMedecin['telM']>;
  posteM: FormControl<IMedecin['posteM']>;
  faxM: FormControl<IMedecin['faxM']>;
  emailM: FormControl<IMedecin['emailM']>;
  hopital: FormControl<IMedecin['hopital']>;
  service: FormControl<IMedecin['service']>;
  image: FormControl<IMedecin['image']>;
  imageContentType: FormControl<IMedecin['imageContentType']>;
  login: FormControl<IMedecin['login']>;
  passwd: FormControl<IMedecin['passwd']>;
  uRL: FormControl<IMedecin['uRL']>;
};

export type MedecinFormGroup = FormGroup<MedecinFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MedecinFormService {
  createMedecinFormGroup(medecin: MedecinFormGroupInput = { id: null }): MedecinFormGroup {
    const medecinRawValue = {
      ...this.getFormDefaults(),
      ...medecin,
    };
    return new FormGroup<MedecinFormGroupContent>({
      id: new FormControl(
        { value: medecinRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      cIN: new FormControl(medecinRawValue.cIN),
      nomMedecin: new FormControl(medecinRawValue.nomMedecin),
      prenomMedecin: new FormControl(medecinRawValue.prenomMedecin),
      grade: new FormControl(medecinRawValue.grade),
      typeMedecin: new FormControl(medecinRawValue.typeMedecin),
      gouvernoratM: new FormControl(medecinRawValue.gouvernoratM),
      adresseM: new FormControl(medecinRawValue.adresseM),
      telM: new FormControl(medecinRawValue.telM),
      posteM: new FormControl(medecinRawValue.posteM),
      faxM: new FormControl(medecinRawValue.faxM),
      emailM: new FormControl(medecinRawValue.emailM),
      hopital: new FormControl(medecinRawValue.hopital),
      service: new FormControl(medecinRawValue.service),
      image: new FormControl(medecinRawValue.image),
      imageContentType: new FormControl(medecinRawValue.imageContentType),
      login: new FormControl(medecinRawValue.login),
      passwd: new FormControl(medecinRawValue.passwd),
      uRL: new FormControl(medecinRawValue.uRL),
    });
  }

  getMedecin(form: MedecinFormGroup): IMedecin | NewMedecin {
    return form.getRawValue() as IMedecin | NewMedecin;
  }

  resetForm(form: MedecinFormGroup, medecin: MedecinFormGroupInput): void {
    const medecinRawValue = { ...this.getFormDefaults(), ...medecin };
    form.reset(
      {
        ...medecinRawValue,
        id: { value: medecinRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): MedecinFormDefaults {
    return {
      id: null,
    };
  }
}
