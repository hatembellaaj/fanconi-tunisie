import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { AndrogeneDetailComponent } from './androgene-detail.component';

describe('Androgene Management Detail Component', () => {
  let comp: AndrogeneDetailComponent;
  let fixture: ComponentFixture<AndrogeneDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AndrogeneDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./androgene-detail.component').then(m => m.AndrogeneDetailComponent),
              resolve: { androgene: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AndrogeneDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AndrogeneDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load androgene on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AndrogeneDetailComponent);

      // THEN
      expect(instance.androgene()).toEqual(expect.objectContaining({ id: 123 }));
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
