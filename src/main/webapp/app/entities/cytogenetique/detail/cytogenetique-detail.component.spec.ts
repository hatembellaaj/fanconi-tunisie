import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { CytogenetiqueDetailComponent } from './cytogenetique-detail.component';

describe('Cytogenetique Management Detail Component', () => {
  let comp: CytogenetiqueDetailComponent;
  let fixture: ComponentFixture<CytogenetiqueDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CytogenetiqueDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./cytogenetique-detail.component').then(m => m.CytogenetiqueDetailComponent),
              resolve: { cytogenetique: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(CytogenetiqueDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CytogenetiqueDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load cytogenetique on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CytogenetiqueDetailComponent);

      // THEN
      expect(instance.cytogenetique()).toEqual(expect.objectContaining({ id: 123 }));
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
