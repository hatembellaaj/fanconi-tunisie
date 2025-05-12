import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { LaboratoireDetailComponent } from './laboratoire-detail.component';

describe('Laboratoire Management Detail Component', () => {
  let comp: LaboratoireDetailComponent;
  let fixture: ComponentFixture<LaboratoireDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LaboratoireDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./laboratoire-detail.component').then(m => m.LaboratoireDetailComponent),
              resolve: { laboratoire: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(LaboratoireDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LaboratoireDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load laboratoire on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', LaboratoireDetailComponent);

      // THEN
      expect(instance.laboratoire()).toEqual(expect.objectContaining({ id: 123 }));
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
