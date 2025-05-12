import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../frere.test-samples';

import { FrereFormService } from './frere-form.service';

describe('Frere Form Service', () => {
  let service: FrereFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FrereFormService);
  });

  describe('Service methods', () => {
    describe('createFrereFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFrereFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nDossierF: expect.any(Object),
            idFrere: expect.any(Object),
            prenomFrere: expect.any(Object),
            atteint: expect.any(Object),
            placefratrie: expect.any(Object),
            sexeF: expect.any(Object),
            decedes: expect.any(Object),
            age: expect.any(Object),
          }),
        );
      });

      it('passing IFrere should create a new form with FormGroup', () => {
        const formGroup = service.createFrereFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nDossierF: expect.any(Object),
            idFrere: expect.any(Object),
            prenomFrere: expect.any(Object),
            atteint: expect.any(Object),
            placefratrie: expect.any(Object),
            sexeF: expect.any(Object),
            decedes: expect.any(Object),
            age: expect.any(Object),
          }),
        );
      });
    });

    describe('getFrere', () => {
      it('should return NewFrere for default Frere initial value', () => {
        const formGroup = service.createFrereFormGroup(sampleWithNewData);

        const frere = service.getFrere(formGroup) as any;

        expect(frere).toMatchObject(sampleWithNewData);
      });

      it('should return NewFrere for empty Frere initial value', () => {
        const formGroup = service.createFrereFormGroup();

        const frere = service.getFrere(formGroup) as any;

        expect(frere).toMatchObject({});
      });

      it('should return IFrere', () => {
        const formGroup = service.createFrereFormGroup(sampleWithRequiredData);

        const frere = service.getFrere(formGroup) as any;

        expect(frere).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFrere should not enable id FormControl', () => {
        const formGroup = service.createFrereFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFrere should disable id FormControl', () => {
        const formGroup = service.createFrereFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
