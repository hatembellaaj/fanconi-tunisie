import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { AndrogeneService } from '../service/androgene.service';
import { IAndrogene } from '../androgene.model';
import { AndrogeneFormService } from './androgene-form.service';

import { AndrogeneUpdateComponent } from './androgene-update.component';

describe('Androgene Management Update Component', () => {
  let comp: AndrogeneUpdateComponent;
  let fixture: ComponentFixture<AndrogeneUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let androgeneFormService: AndrogeneFormService;
  let androgeneService: AndrogeneService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [AndrogeneUpdateComponent],
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
      .overrideTemplate(AndrogeneUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AndrogeneUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    androgeneFormService = TestBed.inject(AndrogeneFormService);
    androgeneService = TestBed.inject(AndrogeneService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const androgene: IAndrogene = { id: 456 };

      activatedRoute.data = of({ androgene });
      comp.ngOnInit();

      expect(comp.androgene).toEqual(androgene);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAndrogene>>();
      const androgene = { id: 123 };
      jest.spyOn(androgeneFormService, 'getAndrogene').mockReturnValue(androgene);
      jest.spyOn(androgeneService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ androgene });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: androgene }));
      saveSubject.complete();

      // THEN
      expect(androgeneFormService.getAndrogene).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(androgeneService.update).toHaveBeenCalledWith(expect.objectContaining(androgene));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAndrogene>>();
      const androgene = { id: 123 };
      jest.spyOn(androgeneFormService, 'getAndrogene').mockReturnValue({ id: null });
      jest.spyOn(androgeneService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ androgene: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: androgene }));
      saveSubject.complete();

      // THEN
      expect(androgeneFormService.getAndrogene).toHaveBeenCalled();
      expect(androgeneService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAndrogene>>();
      const androgene = { id: 123 };
      jest.spyOn(androgeneService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ androgene });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(androgeneService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
