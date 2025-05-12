import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../cytogenetique.test-samples';

import { CytogenetiqueFormService } from './cytogenetique-form.service';

describe('Cytogenetique Form Service', () => {
  let service: CytogenetiqueFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CytogenetiqueFormService);
  });

  describe('Service methods', () => {
    describe('createCytogenetiqueFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCytogenetiqueFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nDossierPatient: expect.any(Object),
            nEtudeCyto: expect.any(Object),
            lymphocytes: expect.any(Object),
            dateSang: expect.any(Object),
            laboratoire: expect.any(Object),
            agentPontant: expect.any(Object),
            instabilite: expect.any(Object),
            instabilitePourcentage: expect.any(Object),
            iR: expect.any(Object),
            iRPourcentage: expect.any(Object),
            moelle: expect.any(Object),
            dateMoelle: expect.any(Object),
            resultatMoelle: expect.any(Object),
          }),
        );
      });

      it('passing ICytogenetique should create a new form with FormGroup', () => {
        const formGroup = service.createCytogenetiqueFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nDossierPatient: expect.any(Object),
            nEtudeCyto: expect.any(Object),
            lymphocytes: expect.any(Object),
            dateSang: expect.any(Object),
            laboratoire: expect.any(Object),
            agentPontant: expect.any(Object),
            instabilite: expect.any(Object),
            instabilitePourcentage: expect.any(Object),
            iR: expect.any(Object),
            iRPourcentage: expect.any(Object),
            moelle: expect.any(Object),
            dateMoelle: expect.any(Object),
            resultatMoelle: expect.any(Object),
          }),
        );
      });
    });

    describe('getCytogenetique', () => {
      it('should return NewCytogenetique for default Cytogenetique initial value', () => {
        const formGroup = service.createCytogenetiqueFormGroup(sampleWithNewData);

        const cytogenetique = service.getCytogenetique(formGroup) as any;

        expect(cytogenetique).toMatchObject(sampleWithNewData);
      });

      it('should return NewCytogenetique for empty Cytogenetique initial value', () => {
        const formGroup = service.createCytogenetiqueFormGroup();

        const cytogenetique = service.getCytogenetique(formGroup) as any;

        expect(cytogenetique).toMatchObject({});
      });

      it('should return ICytogenetique', () => {
        const formGroup = service.createCytogenetiqueFormGroup(sampleWithRequiredData);

        const cytogenetique = service.getCytogenetique(formGroup) as any;

        expect(cytogenetique).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICytogenetique should not enable id FormControl', () => {
        const formGroup = service.createCytogenetiqueFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCytogenetique should disable id FormControl', () => {
        const formGroup = service.createCytogenetiqueFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
