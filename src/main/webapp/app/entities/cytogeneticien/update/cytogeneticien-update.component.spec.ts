import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { CytogeneticienService } from '../service/cytogeneticien.service';
import { ICytogeneticien } from '../cytogeneticien.model';
import { CytogeneticienFormService } from './cytogeneticien-form.service';

import { CytogeneticienUpdateComponent } from './cytogeneticien-update.component';

describe('Cytogeneticien Management Update Component', () => {
  let comp: CytogeneticienUpdateComponent;
  let fixture: ComponentFixture<CytogeneticienUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cytogeneticienFormService: CytogeneticienFormService;
  let cytogeneticienService: CytogeneticienService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [CytogeneticienUpdateComponent],
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
      .overrideTemplate(CytogeneticienUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CytogeneticienUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cytogeneticienFormService = TestBed.inject(CytogeneticienFormService);
    cytogeneticienService = TestBed.inject(CytogeneticienService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const cytogeneticien: ICytogeneticien = { id: 456 };

      activatedRoute.data = of({ cytogeneticien });
      comp.ngOnInit();

      expect(comp.cytogeneticien).toEqual(cytogeneticien);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICytogeneticien>>();
      const cytogeneticien = { id: 123 };
      jest.spyOn(cytogeneticienFormService, 'getCytogeneticien').mockReturnValue(cytogeneticien);
      jest.spyOn(cytogeneticienService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cytogeneticien });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cytogeneticien }));
      saveSubject.complete();

      // THEN
      expect(cytogeneticienFormService.getCytogeneticien).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(cytogeneticienService.update).toHaveBeenCalledWith(expect.objectContaining(cytogeneticien));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICytogeneticien>>();
      const cytogeneticien = { id: 123 };
      jest.spyOn(cytogeneticienFormService, 'getCytogeneticien').mockReturnValue({ id: null });
      jest.spyOn(cytogeneticienService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cytogeneticien: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cytogeneticien }));
      saveSubject.complete();

      // THEN
      expect(cytogeneticienFormService.getCytogeneticien).toHaveBeenCalled();
      expect(cytogeneticienService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICytogeneticien>>();
      const cytogeneticien = { id: 123 };
      jest.spyOn(cytogeneticienService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cytogeneticien });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cytogeneticienService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
