import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { CytogenetiqueService } from '../service/cytogenetique.service';
import { ICytogenetique } from '../cytogenetique.model';
import { CytogenetiqueFormService } from './cytogenetique-form.service';

import { CytogenetiqueUpdateComponent } from './cytogenetique-update.component';

describe('Cytogenetique Management Update Component', () => {
  let comp: CytogenetiqueUpdateComponent;
  let fixture: ComponentFixture<CytogenetiqueUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cytogenetiqueFormService: CytogenetiqueFormService;
  let cytogenetiqueService: CytogenetiqueService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [CytogenetiqueUpdateComponent],
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
      .overrideTemplate(CytogenetiqueUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CytogenetiqueUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cytogenetiqueFormService = TestBed.inject(CytogenetiqueFormService);
    cytogenetiqueService = TestBed.inject(CytogenetiqueService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const cytogenetique: ICytogenetique = { id: 456 };

      activatedRoute.data = of({ cytogenetique });
      comp.ngOnInit();

      expect(comp.cytogenetique).toEqual(cytogenetique);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICytogenetique>>();
      const cytogenetique = { id: 123 };
      jest.spyOn(cytogenetiqueFormService, 'getCytogenetique').mockReturnValue(cytogenetique);
      jest.spyOn(cytogenetiqueService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cytogenetique });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cytogenetique }));
      saveSubject.complete();

      // THEN
      expect(cytogenetiqueFormService.getCytogenetique).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(cytogenetiqueService.update).toHaveBeenCalledWith(expect.objectContaining(cytogenetique));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICytogenetique>>();
      const cytogenetique = { id: 123 };
      jest.spyOn(cytogenetiqueFormService, 'getCytogenetique').mockReturnValue({ id: null });
      jest.spyOn(cytogenetiqueService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cytogenetique: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cytogenetique }));
      saveSubject.complete();

      // THEN
      expect(cytogenetiqueFormService.getCytogenetique).toHaveBeenCalled();
      expect(cytogenetiqueService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICytogenetique>>();
      const cytogenetique = { id: 123 };
      jest.spyOn(cytogenetiqueService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cytogenetique });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cytogenetiqueService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
