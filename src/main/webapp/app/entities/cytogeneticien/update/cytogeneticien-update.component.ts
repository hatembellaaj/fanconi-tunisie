import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { CytogeneticienService } from '../service/cytogeneticien.service';
import { ICytogeneticien } from '../cytogeneticien.model';
import { CytogeneticienFormGroup, CytogeneticienFormService } from './cytogeneticien-form.service';

@Component({
  standalone: true,
  selector: 'jhi-cytogeneticien-update',
  templateUrl: './cytogeneticien-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CytogeneticienUpdateComponent implements OnInit {
  isSaving = false;
  cytogeneticien: ICytogeneticien | null = null;

  protected dataUtils = inject(DataUtils);
  protected eventManager = inject(EventManager);
  protected cytogeneticienService = inject(CytogeneticienService);
  protected cytogeneticienFormService = inject(CytogeneticienFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: CytogeneticienFormGroup = this.cytogeneticienFormService.createCytogeneticienFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cytogeneticien }) => {
      this.cytogeneticien = cytogeneticien;
      if (cytogeneticien) {
        this.updateForm(cytogeneticien);
      }
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('fanconiApp.error', { message: err.message })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cytogeneticien = this.cytogeneticienFormService.getCytogeneticien(this.editForm);
    if (cytogeneticien.id !== null) {
      this.subscribeToSaveResponse(this.cytogeneticienService.update(cytogeneticien));
    } else {
      this.subscribeToSaveResponse(this.cytogeneticienService.create(cytogeneticien));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICytogeneticien>>): void {
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

  protected updateForm(cytogeneticien: ICytogeneticien): void {
    this.cytogeneticien = cytogeneticien;
    this.cytogeneticienFormService.resetForm(this.editForm, cytogeneticien);
  }
}
