import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { ILaboratoire } from '../laboratoire.model';

@Component({
  standalone: true,
  selector: 'jhi-laboratoire-detail',
  templateUrl: './laboratoire-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class LaboratoireDetailComponent {
  laboratoire = input<ILaboratoire | null>(null);

  previousState(): void {
    window.history.back();
  }
}
