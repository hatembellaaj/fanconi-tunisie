import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../cytogeneticien.test-samples';

import { CytogeneticienFormService } from './cytogeneticien-form.service';

describe('Cytogeneticien Form Service', () => {
  let service: CytogeneticienFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CytogeneticienFormService);
  });

  describe('Service methods', () => {
    describe('createCytogeneticienFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCytogeneticienFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            nom: expect.any(Object),
            prenom: expect.any(Object),
            service: expect.any(Object),
            etab: expect.any(Object),
            adresse: expect.any(Object),
            tel: expect.any(Object),
            poste: expect.any(Object),
            fax: expect.any(Object),
            email: expect.any(Object),
            photo: expect.any(Object),
            type: expect.any(Object),
            login: expect.any(Object),
            passwd: expect.any(Object),
            uRL: expect.any(Object),
          }),
        );
      });

      it('passing ICytogeneticien should create a new form with FormGroup', () => {
        const formGroup = service.createCytogeneticienFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            nom: expect.any(Object),
            prenom: expect.any(Object),
            service: expect.any(Object),
            etab: expect.any(Object),
            adresse: expect.any(Object),
            tel: expect.any(Object),
            poste: expect.any(Object),
            fax: expect.any(Object),
            email: expect.any(Object),
            photo: expect.any(Object),
            type: expect.any(Object),
            login: expect.any(Object),
            passwd: expect.any(Object),
            uRL: expect.any(Object),
          }),
        );
      });
    });

    describe('getCytogeneticien', () => {
      it('should return NewCytogeneticien for default Cytogeneticien initial value', () => {
        const formGroup = service.createCytogeneticienFormGroup(sampleWithNewData);

        const cytogeneticien = service.getCytogeneticien(formGroup) as any;

        expect(cytogeneticien).toMatchObject(sampleWithNewData);
      });

      it('should return NewCytogeneticien for empty Cytogeneticien initial value', () => {
        const formGroup = service.createCytogeneticienFormGroup();

        const cytogeneticien = service.getCytogeneticien(formGroup) as any;

        expect(cytogeneticien).toMatchObject({});
      });

      it('should return ICytogeneticien', () => {
        const formGroup = service.createCytogeneticienFormGroup(sampleWithRequiredData);

        const cytogeneticien = service.getCytogeneticien(formGroup) as any;

        expect(cytogeneticien).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICytogeneticien should not enable id FormControl', () => {
        const formGroup = service.createCytogeneticienFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCytogeneticien should disable id FormControl', () => {
        const formGroup = service.createCytogeneticienFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
