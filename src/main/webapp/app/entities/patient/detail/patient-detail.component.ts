import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IPatient } from '../patient.model';

@Component({
  standalone: true,
  selector: 'jhi-patient-detail',
  templateUrl: './patient-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class PatientDetailComponent {
  patient = input<IPatient | null>(null);

  previousState(): void {
    window.history.back();
  }
}
