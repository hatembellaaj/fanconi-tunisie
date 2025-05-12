import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFrere } from '../frere.model';
import { FrereService } from '../service/frere.service';
import { FrereFormGroup, FrereFormService } from './frere-form.service';

@Component({
  standalone: true,
  selector: 'jhi-frere-update',
  templateUrl: './frere-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FrereUpdateComponent implements OnInit {
  isSaving = false;
  frere: IFrere | null = null;

  protected frereService = inject(FrereService);
  protected frereFormService = inject(FrereFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: FrereFormGroup = this.frereFormService.createFrereFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ frere }) => {
      this.frere = frere;
      if (frere) {
        this.updateForm(frere);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const frere = this.frereFormService.getFrere(this.editForm);
    if (frere.id !== null) {
      this.subscribeToSaveResponse(this.frereService.update(frere));
    } else {
      this.subscribeToSaveResponse(this.frereService.create(frere));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFrere>>): void {
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

  protected updateForm(frere: IFrere): void {
    this.frere = frere;
    this.frereFormService.resetForm(this.editForm, frere);
  }
}
