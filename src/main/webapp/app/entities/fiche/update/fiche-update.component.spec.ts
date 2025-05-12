import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { FicheService } from '../service/fiche.service';
import { IFiche } from '../fiche.model';
import { FicheFormService } from './fiche-form.service';

import { FicheUpdateComponent } from './fiche-update.component';

describe('Fiche Management Update Component', () => {
  let comp: FicheUpdateComponent;
  let fixture: ComponentFixture<FicheUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ficheFormService: FicheFormService;
  let ficheService: FicheService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheUpdateComponent],
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
      .overrideTemplate(FicheUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FicheUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ficheFormService = TestBed.inject(FicheFormService);
    ficheService = TestBed.inject(FicheService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const fiche: IFiche = { id: 456 };

      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      expect(comp.fiche).toEqual(fiche);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFiche>>();
      const fiche = { id: 123 };
      jest.spyOn(ficheFormService, 'getFiche').mockReturnValue(fiche);
      jest.spyOn(ficheService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fiche }));
      saveSubject.complete();

      // THEN
      expect(ficheFormService.getFiche).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ficheService.update).toHaveBeenCalledWith(expect.objectContaining(fiche));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFiche>>();
      const fiche = { id: 123 };
      jest.spyOn(ficheFormService, 'getFiche').mockReturnValue({ id: null });
      jest.spyOn(ficheService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fiche: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fiche }));
      saveSubject.complete();

      // THEN
      expect(ficheFormService.getFiche).toHaveBeenCalled();
      expect(ficheService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFiche>>();
      const fiche = { id: 123 };
      jest.spyOn(ficheService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ficheService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
