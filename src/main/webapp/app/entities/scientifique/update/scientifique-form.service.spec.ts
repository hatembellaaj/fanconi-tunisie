import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../scientifique.test-samples';

import { ScientifiqueFormService } from './scientifique-form.service';

describe('Scientifique Form Service', () => {
  let service: ScientifiqueFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ScientifiqueFormService);
  });

  describe('Service methods', () => {
    describe('createScientifiqueFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createScientifiqueFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codeSC: expect.any(Object),
            nomSC: expect.any(Object),
            prenomSC: expect.any(Object),
            serviceSC: expect.any(Object),
            centreSC: expect.any(Object),
            adresseSC: expect.any(Object),
            telSC: expect.any(Object),
            emailSC: expect.any(Object),
            photoSC: expect.any(Object),
            typeSC: expect.any(Object),
            loginSC: expect.any(Object),
            passwdSC: expect.any(Object),
            uRL: expect.any(Object),
          }),
        );
      });

      it('passing IScientifique should create a new form with FormGroup', () => {
        const formGroup = service.createScientifiqueFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codeSC: expect.any(Object),
            nomSC: expect.any(Object),
            prenomSC: expect.any(Object),
            serviceSC: expect.any(Object),
            centreSC: expect.any(Object),
            adresseSC: expect.any(Object),
            telSC: expect.any(Object),
            emailSC: expect.any(Object),
            photoSC: expect.any(Object),
            typeSC: expect.any(Object),
            loginSC: expect.any(Object),
            passwdSC: expect.any(Object),
            uRL: expect.any(Object),
          }),
        );
      });
    });

    describe('getScientifique', () => {
      it('should return NewScientifique for default Scientifique initial value', () => {
        const formGroup = service.createScientifiqueFormGroup(sampleWithNewData);

        const scientifique = service.getScientifique(formGroup) as any;

        expect(scientifique).toMatchObject(sampleWithNewData);
      });

      it('should return NewScientifique for empty Scientifique initial value', () => {
        const formGroup = service.createScientifiqueFormGroup();

        const scientifique = service.getScientifique(formGroup) as any;

        expect(scientifique).toMatchObject({});
      });

      it('should return IScientifique', () => {
        const formGroup = service.createScientifiqueFormGroup(sampleWithRequiredData);

        const scientifique = service.getScientifique(formGroup) as any;

        expect(scientifique).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IScientifique should not enable id FormControl', () => {
        const formGroup = service.createScientifiqueFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewScientifique should disable id FormControl', () => {
        const formGroup = service.createScientifiqueFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
