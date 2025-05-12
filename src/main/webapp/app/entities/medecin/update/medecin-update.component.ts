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
import { MedecinService } from '../service/medecin.service';
import { IMedecin } from '../medecin.model';
import { MedecinFormGroup, MedecinFormService } from './medecin-form.service';

@Component({
  standalone: true,
  selector: 'jhi-medecin-update',
  templateUrl: './medecin-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class MedecinUpdateComponent implements OnInit {
  isSaving = false;
  medecin: IMedecin | null = null;

  protected dataUtils = inject(DataUtils);
  protected eventManager = inject(EventManager);
  protected medecinService = inject(MedecinService);
  protected medecinFormService = inject(MedecinFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: MedecinFormGroup = this.medecinFormService.createMedecinFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medecin }) => {
      this.medecin = medecin;
      if (medecin) {
        this.updateForm(medecin);
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
    const medecin = this.medecinFormService.getMedecin(this.editForm);
    if (medecin.id !== null) {
      this.subscribeToSaveResponse(this.medecinService.update(medecin));
    } else {
      this.subscribeToSaveResponse(this.medecinService.create(medecin));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedecin>>): void {
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

  protected updateForm(medecin: IMedecin): void {
    this.medecin = medecin;
    this.medecinFormService.resetForm(this.editForm, medecin);
  }
}
