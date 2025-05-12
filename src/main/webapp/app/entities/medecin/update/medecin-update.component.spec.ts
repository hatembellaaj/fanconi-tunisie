import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { MedecinService } from '../service/medecin.service';
import { IMedecin } from '../medecin.model';
import { MedecinFormService } from './medecin-form.service';

import { MedecinUpdateComponent } from './medecin-update.component';

describe('Medecin Management Update Component', () => {
  let comp: MedecinUpdateComponent;
  let fixture: ComponentFixture<MedecinUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let medecinFormService: MedecinFormService;
  let medecinService: MedecinService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [MedecinUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(MedecinUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MedecinUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    medecinFormService = TestBed.inject(MedecinFormService);
    medecinService = TestBed.inject(MedecinService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const medecin: IMedecin = { id: 456 };

      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      expect(comp.medecin).toEqual(medecin);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMedecin>>();
      const medecin = { id: 123 };
      jest.spyOn(medecinFormService, 'getMedecin').mockReturnValue(medecin);
      jest.spyOn(medecinService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: medecin }));
      saveSubject.complete();

      // THEN
      expect(medecinFormService.getMedecin).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(medecinService.update).toHaveBeenCalledWith(expect.objectContaining(medecin));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMedecin>>();
      const medecin = { id: 123 };
      jest.spyOn(medecinFormService, 'getMedecin').mockReturnValue({ id: null });
      jest.spyOn(medecinService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ medecin: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: medecin }));
      saveSubject.complete();

      // THEN
      expect(medecinFormService.getMedecin).toHaveBeenCalled();
      expect(medecinService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMedecin>>();
      const medecin = { id: 123 };
      jest.spyOn(medecinService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(medecinService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
