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
import { ScientifiqueService } from '../service/scientifique.service';
import { IScientifique } from '../scientifique.model';
import { ScientifiqueFormGroup, ScientifiqueFormService } from './scientifique-form.service';

@Component({
  standalone: true,
  selector: 'jhi-scientifique-update',
  templateUrl: './scientifique-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ScientifiqueUpdateComponent implements OnInit {
  isSaving = false;
  scientifique: IScientifique | null = null;

  protected dataUtils = inject(DataUtils);
  protected eventManager = inject(EventManager);
  protected scientifiqueService = inject(ScientifiqueService);
  protected scientifiqueFormService = inject(ScientifiqueFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ScientifiqueFormGroup = this.scientifiqueFormService.createScientifiqueFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ scientifique }) => {
      this.scientifique = scientifique;
      if (scientifique) {
        this.updateForm(scientifique);
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
    const scientifique = this.scientifiqueFormService.getScientifique(this.editForm);
    if (scientifique.id !== null) {
      this.subscribeToSaveResponse(this.scientifiqueService.update(scientifique));
    } else {
      this.subscribeToSaveResponse(this.scientifiqueService.create(scientifique));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IScientifique>>): void {
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

  protected updateForm(scientifique: IScientifique): void {
    this.scientifique = scientifique;
    this.scientifiqueFormService.resetForm(this.editForm, scientifique);
  }
}
