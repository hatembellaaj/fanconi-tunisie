import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ICousin } from '../cousin.model';
import { CousinService } from '../service/cousin.service';
import { CousinFormGroup, CousinFormService } from './cousin-form.service';

@Component({
  standalone: true,
  selector: 'jhi-cousin-update',
  templateUrl: './cousin-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CousinUpdateComponent implements OnInit {
  isSaving = false;
  cousin: ICousin | null = null;

  protected cousinService = inject(CousinService);
  protected cousinFormService = inject(CousinFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: CousinFormGroup = this.cousinFormService.createCousinFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cousin }) => {
      this.cousin = cousin;
      if (cousin) {
        this.updateForm(cousin);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cousin = this.cousinFormService.getCousin(this.editForm);
    if (cousin.id !== null) {
      this.subscribeToSaveResponse(this.cousinService.update(cousin));
    } else {
      this.subscribeToSaveResponse(this.cousinService.create(cousin));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICousin>>): void {
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

  protected updateForm(cousin: ICousin): void {
    this.cousin = cousin;
    this.cousinFormService.resetForm(this.editForm, cousin);
  }
}
