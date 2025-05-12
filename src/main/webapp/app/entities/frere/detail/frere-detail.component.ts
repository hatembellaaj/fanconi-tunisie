import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IFrere } from '../frere.model';

@Component({
  standalone: true,
  selector: 'jhi-frere-detail',
  templateUrl: './frere-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FrereDetailComponent {
  frere = input<IFrere | null>(null);

  previousState(): void {
    window.history.back();
  }
}
