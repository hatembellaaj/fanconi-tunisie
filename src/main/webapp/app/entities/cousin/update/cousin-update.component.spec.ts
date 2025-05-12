import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { CousinService } from '../service/cousin.service';
import { ICousin } from '../cousin.model';
import { CousinFormService } from './cousin-form.service';

import { CousinUpdateComponent } from './cousin-update.component';

describe('Cousin Management Update Component', () => {
  let comp: CousinUpdateComponent;
  let fixture: ComponentFixture<CousinUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cousinFormService: CousinFormService;
  let cousinService: CousinService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [CousinUpdateComponent],
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
      .overrideTemplate(CousinUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CousinUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cousinFormService = TestBed.inject(CousinFormService);
    cousinService = TestBed.inject(CousinService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const cousin: ICousin = { id: 456 };

      activatedRoute.data = of({ cousin });
      comp.ngOnInit();

      expect(comp.cousin).toEqual(cousin);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICousin>>();
      const cousin = { id: 123 };
      jest.spyOn(cousinFormService, 'getCousin').mockReturnValue(cousin);
      jest.spyOn(cousinService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cousin });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cousin }));
      saveSubject.complete();

      // THEN
      expect(cousinFormService.getCousin).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(cousinService.update).toHaveBeenCalledWith(expect.objectContaining(cousin));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICousin>>();
      const cousin = { id: 123 };
      jest.spyOn(cousinFormService, 'getCousin').mockReturnValue({ id: null });
      jest.spyOn(cousinService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cousin: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cousin }));
      saveSubject.complete();

      // THEN
      expect(cousinFormService.getCousin).toHaveBeenCalled();
      expect(cousinService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICousin>>();
      const cousin = { id: 123 };
      jest.spyOn(cousinService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cousin });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cousinService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
