import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IMembre } from '../membre.model';

@Component({
  standalone: true,
  selector: 'jhi-membre-detail',
  templateUrl: './membre-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class MembreDetailComponent {
  membre = input<IMembre | null>(null);

  previousState(): void {
    window.history.back();
  }
}
