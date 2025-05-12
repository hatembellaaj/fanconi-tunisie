import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAndrogene } from '../androgene.model';
import { AndrogeneService } from '../service/androgene.service';
import { AndrogeneFormGroup, AndrogeneFormService } from './androgene-form.service';

@Component({
  standalone: true,
  selector: 'jhi-androgene-update',
  templateUrl: './androgene-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AndrogeneUpdateComponent implements OnInit {
  isSaving = false;
  androgene: IAndrogene | null = null;

  protected androgeneService = inject(AndrogeneService);
  protected androgeneFormService = inject(AndrogeneFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AndrogeneFormGroup = this.androgeneFormService.createAndrogeneFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ androgene }) => {
      this.androgene = androgene;
      if (androgene) {
        this.updateForm(androgene);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const androgene = this.androgeneFormService.getAndrogene(this.editForm);
    if (androgene.id !== null) {
      this.subscribeToSaveResponse(this.androgeneService.update(androgene));
    } else {
      this.subscribeToSaveResponse(this.androgeneService.create(androgene));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAndrogene>>): void {
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

  protected updateForm(androgene: IAndrogene): void {
    this.androgene = androgene;
    this.androgeneFormService.resetForm(this.editForm, androgene);
  }
}
