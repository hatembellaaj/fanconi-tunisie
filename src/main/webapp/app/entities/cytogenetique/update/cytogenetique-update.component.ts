import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ICytogenetique } from '../cytogenetique.model';
import { CytogenetiqueService } from '../service/cytogenetique.service';
import { CytogenetiqueFormGroup, CytogenetiqueFormService } from './cytogenetique-form.service';

@Component({
  standalone: true,
  selector: 'jhi-cytogenetique-update',
  templateUrl: './cytogenetique-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CytogenetiqueUpdateComponent implements OnInit {
  isSaving = false;
  cytogenetique: ICytogenetique | null = null;

  protected cytogenetiqueService = inject(CytogenetiqueService);
  protected cytogenetiqueFormService = inject(CytogenetiqueFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: CytogenetiqueFormGroup = this.cytogenetiqueFormService.createCytogenetiqueFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cytogenetique }) => {
      this.cytogenetique = cytogenetique;
      if (cytogenetique) {
        this.updateForm(cytogenetique);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cytogenetique = this.cytogenetiqueFormService.getCytogenetique(this.editForm);
    if (cytogenetique.id !== null) {
      this.subscribeToSaveResponse(this.cytogenetiqueService.update(cytogenetique));
    } else {
      this.subscribeToSaveResponse(this.cytogenetiqueService.create(cytogenetique));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICytogenetique>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(cytogenetique: ICytogenetique): void {
    this.cytogenetique = cytogenetique;
    this.cytogenetiqueFormService.resetForm(this.editForm, cytogenetique);
  }
}
