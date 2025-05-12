import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { ScientifiqueService } from '../service/scientifique.service';
import { IScientifique } from '../scientifique.model';
import { ScientifiqueFormService } from './scientifique-form.service';

import { ScientifiqueUpdateComponent } from './scientifique-update.component';

describe('Scientifique Management Update Component', () => {
  let comp: ScientifiqueUpdateComponent;
  let fixture: ComponentFixture<ScientifiqueUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let scientifiqueFormService: ScientifiqueFormService;
  let scientifiqueService: ScientifiqueService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ScientifiqueUpdateComponent],
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
      .overrideTemplate(ScientifiqueUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ScientifiqueUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    scientifiqueFormService = TestBed.inject(ScientifiqueFormService);
    scientifiqueService = TestBed.inject(ScientifiqueService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const scientifique: IScientifique = { id: 456 };

      activatedRoute.data = of({ scientifique });
      comp.ngOnInit();

      expect(comp.scientifique).toEqual(scientifique);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IScientifique>>();
      const scientifique = { id: 123 };
      jest.spyOn(scientifiqueFormService, 'getScientifique').mockReturnValue(scientifique);
      jest.spyOn(scientifiqueService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ scientifique });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: scientifique }));
      saveSubject.complete();

      // THEN
      expect(scientifiqueFormService.getScientifique).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(scientifiqueService.update).toHaveBeenCalledWith(expect.objectContaining(scientifique));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IScientifique>>();
      const scientifique = { id: 123 };
      jest.spyOn(scientifiqueFormService, 'getScientifique').mockReturnValue({ id: null });
      jest.spyOn(scientifiqueService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ scientifique: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: scientifique }));
      saveSubject.complete();

      // THEN
      expect(scientifiqueFormService.getScientifique).toHaveBeenCalled();
      expect(scientifiqueService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IScientifique>>();
      const scientifique = { id: 123 };
      jest.spyOn(scientifiqueService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ scientifique });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(scientifiqueService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
