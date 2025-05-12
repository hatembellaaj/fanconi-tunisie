import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { FrereService } from '../service/frere.service';
import { IFrere } from '../frere.model';
import { FrereFormService } from './frere-form.service';

import { FrereUpdateComponent } from './frere-update.component';

describe('Frere Management Update Component', () => {
  let comp: FrereUpdateComponent;
  let fixture: ComponentFixture<FrereUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let frereFormService: FrereFormService;
  let frereService: FrereService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FrereUpdateComponent],
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
      .overrideTemplate(FrereUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FrereUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    frereFormService = TestBed.inject(FrereFormService);
    frereService = TestBed.inject(FrereService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const frere: IFrere = { id: 456 };

      activatedRoute.data = of({ frere });
      comp.ngOnInit();

      expect(comp.frere).toEqual(frere);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFrere>>();
      const frere = { id: 123 };
      jest.spyOn(frereFormService, 'getFrere').mockReturnValue(frere);
      jest.spyOn(frereService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ frere });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: frere }));
      saveSubject.complete();

      // THEN
      expect(frereFormService.getFrere).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(frereService.update).toHaveBeenCalledWith(expect.objectContaining(frere));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFrere>>();
      const frere = { id: 123 };
      jest.spyOn(frereFormService, 'getFrere').mockReturnValue({ id: null });
      jest.spyOn(frereService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ frere: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: frere }));
      saveSubject.complete();

      // THEN
      expect(frereFormService.getFrere).toHaveBeenCalled();
      expect(frereService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFrere>>();
      const frere = { id: 123 };
      jest.spyOn(frereService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ frere });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(frereService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
