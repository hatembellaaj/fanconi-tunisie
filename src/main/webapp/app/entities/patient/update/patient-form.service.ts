import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IPatient, NewPatient } from '../patient.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPatient for edit and NewPatientFormGroupInput for create.
 */
type PatientFormGroupInput = IPatient | PartialWithRequiredKeyOf<NewPatient>;

type PatientFormDefaults = Pick<NewPatient, 'id'>;

type PatientFormGroupContent = {
  id: FormControl<IPatient['id'] | NewPatient['id']>;
  ndossierP: FormControl<IPatient['ndossierP']>;
  nom: FormControl<IPatient['nom']>;
  prenom: FormControl<IPatient['prenom']>;
  dateNaissance: FormControl<IPatient['dateNaissance']>;
  lieuNaissance: FormControl<IPatient['lieuNaissance']>;
  sexe: FormControl<IPatient['sexe']>;
  gouvernorat: FormControl<IPatient['gouvernorat']>;
  adresse: FormControl<IPatient['adresse']>;
  reperes: FormControl<IPatient['reperes']>;
  tel: FormControl<IPatient['tel']>;
  prenomPere: FormControl<IPatient['prenomPere']>;
  nomMere: FormControl<IPatient['nomMere']>;
  prenomMere: FormControl<IPatient['prenomMere']>;
  nomGMP: FormControl<IPatient['nomGMP']>;
  nomGMM: FormControl<IPatient['nomGMM']>;
  age: FormControl<IPatient['age']>;
};

export type PatientFormGroup = FormGroup<PatientFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PatientFormService {
  createPatientFormGroup(patient: PatientFormGroupInput = { id: null }): PatientFormGroup {
    const patientRawValue = {
      ...this.getFormDefaults(),
      ...patient,
    };
    return new FormGroup<PatientFormGroupContent>({
      id: new FormControl(
        { value: patientRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      ndossierP: new FormControl(patientRawValue.ndossierP),
      nom: new FormControl(patientRawValue.nom),
      prenom: new FormControl(patientRawValue.prenom),
      dateNaissance: new FormControl(patientRawValue.dateNaissance),
      lieuNaissance: new FormControl(patientRawValue.lieuNaissance),
      sexe: new FormControl(patientRawValue.sexe),
      gouvernorat: new FormControl(patientRawValue.gouvernorat),
      adresse: new FormControl(patientRawValue.adresse),
      reperes: new FormControl(patientRawValue.reperes),
      tel: new FormControl(patientRawValue.tel),
      prenomPere: new FormControl(patientRawValue.prenomPere),
      nomMere: new FormControl(patientRawValue.nomMere),
      prenomMere: new FormControl(patientRawValue.prenomMere),
      nomGMP: new FormControl(patientRawValue.nomGMP),
      nomGMM: new FormControl(patientRawValue.nomGMM),
      age: new FormControl(patientRawValue.age),
    });
  }

  getPatient(form: PatientFormGroup): IPatient | NewPatient {
    return form.getRawValue() as IPatient | NewPatient;
  }

  resetForm(form: PatientFormGroup, patient: PatientFormGroupInput): void {
    const patientRawValue = { ...this.getFormDefaults(), ...patient };
    form.reset(
      {
        ...patientRawValue,
        id: { value: patientRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): PatientFormDefaults {
    return {
      id: null,
    };
  }
}
