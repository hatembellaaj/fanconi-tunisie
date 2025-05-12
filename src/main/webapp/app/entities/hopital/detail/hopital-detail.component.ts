import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IHopital } from '../hopital.model';

@Component({
  standalone: true,
  selector: 'jhi-hopital-detail',
  templateUrl: './hopital-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class HopitalDetailComponent {
  hopital = input<IHopital | null>(null);

  previousState(): void {
    window.history.back();
  }
}
