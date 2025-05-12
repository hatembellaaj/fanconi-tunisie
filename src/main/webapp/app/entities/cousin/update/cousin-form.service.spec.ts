import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../cousin.test-samples';

import { CousinFormService } from './cousin-form.service';

describe('Cousin Form Service', () => {
  let service: CousinFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CousinFormService);
  });

  describe('Service methods', () => {
    describe('createCousinFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCousinFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ndossierC: expect.any(Object),
            idCousin: expect.any(Object),
            prenomCousin: expect.any(Object),
            placeCousin: expect.any(Object),
            sexeC: expect.any(Object),
          }),
        );
      });

      it('passing ICousin should create a new form with FormGroup', () => {
        const formGroup = service.createCousinFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ndossierC: expect.any(Object),
            idCousin: expect.any(Object),
            prenomCousin: expect.any(Object),
            placeCousin: expect.any(Object),
            sexeC: expect.any(Object),
          }),
        );
      });
    });

    describe('getCousin', () => {
      it('should return NewCousin for default Cousin initial value', () => {
        const formGroup = service.createCousinFormGroup(sampleWithNewData);

        const cousin = service.getCousin(formGroup) as any;

        expect(cousin).toMatchObject(sampleWithNewData);
      });

      it('should return NewCousin for empty Cousin initial value', () => {
        const formGroup = service.createCousinFormGroup();

        const cousin = service.getCousin(formGroup) as any;

        expect(cousin).toMatchObject({});
      });

      it('should return ICousin', () => {
        const formGroup = service.createCousinFormGroup(sampleWithRequiredData);

        const cousin = service.getCousin(formGroup) as any;

        expect(cousin).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICousin should not enable id FormControl', () => {
        const formGroup = service.createCousinFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCousin should disable id FormControl', () => {
        const formGroup = service.createCousinFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
