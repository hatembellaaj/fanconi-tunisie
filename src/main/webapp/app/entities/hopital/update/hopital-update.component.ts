import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IHopital } from '../hopital.model';
import { HopitalService } from '../service/hopital.service';
import { HopitalFormGroup, HopitalFormService } from './hopital-form.service';

@Component({
  standalone: true,
  selector: 'jhi-hopital-update',
  templateUrl: './hopital-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class HopitalUpdateComponent implements OnInit {
  isSaving = false;
  hopital: IHopital | null = null;

  protected hopitalService = inject(HopitalService);
  protected hopitalFormService = inject(HopitalFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: HopitalFormGroup = this.hopitalFormService.createHopitalFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hopital }) => {
      this.hopital = hopital;
      if (hopital) {
        this.updateForm(hopital);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hopital = this.hopitalFormService.getHopital(this.editForm);
    if (hopital.id !== null) {
      this.subscribeToSaveResponse(this.hopitalService.update(hopital));
    } else {
      this.subscribeToSaveResponse(this.hopitalService.create(hopital));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHopital>>): void {
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

  protected updateForm(hopital: IHopital): void {
    this.hopital = hopital;
    this.hopitalFormService.resetForm(this.editForm, hopital);
  }
}
