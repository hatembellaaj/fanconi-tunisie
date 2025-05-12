import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../androgene.test-samples';

import { AndrogeneFormService } from './androgene-form.service';

describe('Androgene Form Service', () => {
  let service: AndrogeneFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AndrogeneFormService);
  });

  describe('Service methods', () => {
    describe('createAndrogeneFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAndrogeneFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nDossierPa: expect.any(Object),
            mois: expect.any(Object),
            reponse: expect.any(Object),
          }),
        );
      });

      it('passing IAndrogene should create a new form with FormGroup', () => {
        const formGroup = service.createAndrogeneFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nDossierPa: expect.any(Object),
            mois: expect.any(Object),
            reponse: expect.any(Object),
          }),
        );
      });
    });

    describe('getAndrogene', () => {
      it('should return NewAndrogene for default Androgene initial value', () => {
        const formGroup = service.createAndrogeneFormGroup(sampleWithNewData);

        const androgene = service.getAndrogene(formGroup) as any;

        expect(androgene).toMatchObject(sampleWithNewData);
      });

      it('should return NewAndrogene for empty Androgene initial value', () => {
        const formGroup = service.createAndrogeneFormGroup();

        const androgene = service.getAndrogene(formGroup) as any;

        expect(androgene).toMatchObject({});
      });

      it('should return IAndrogene', () => {
        const formGroup = service.createAndrogeneFormGroup(sampleWithRequiredData);

        const androgene = service.getAndrogene(formGroup) as any;

        expect(androgene).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAndrogene should not enable id FormControl', () => {
        const formGroup = service.createAndrogeneFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAndrogene should disable id FormControl', () => {
        const formGroup = service.createAndrogeneFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
