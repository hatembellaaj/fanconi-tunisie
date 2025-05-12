import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ILaboratoire } from '../laboratoire.model';
import { LaboratoireService } from '../service/laboratoire.service';
import { LaboratoireFormGroup, LaboratoireFormService } from './laboratoire-form.service';

@Component({
  standalone: true,
  selector: 'jhi-laboratoire-update',
  templateUrl: './laboratoire-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class LaboratoireUpdateComponent implements OnInit {
  isSaving = false;
  laboratoire: ILaboratoire | null = null;

  protected laboratoireService = inject(LaboratoireService);
  protected laboratoireFormService = inject(LaboratoireFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: LaboratoireFormGroup = this.laboratoireFormService.createLaboratoireFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ laboratoire }) => {
      this.laboratoire = laboratoire;
      if (laboratoire) {
        this.updateForm(laboratoire);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const laboratoire = this.laboratoireFormService.getLaboratoire(this.editForm);
    if (laboratoire.id !== null) {
      this.subscribeToSaveResponse(this.laboratoireService.update(laboratoire));
    } else {
      this.subscribeToSaveResponse(this.laboratoireService.create(laboratoire));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILaboratoire>>): void {
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

  protected updateForm(laboratoire: ILaboratoire): void {
    this.laboratoire = laboratoire;
    this.laboratoireFormService.resetForm(this.editForm, laboratoire);
  }
}
