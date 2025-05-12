import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IAndrogene } from '../androgene.model';

@Component({
  standalone: true,
  selector: 'jhi-androgene-detail',
  templateUrl: './androgene-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AndrogeneDetailComponent {
  androgene = input<IAndrogene | null>(null);

  previousState(): void {
    window.history.back();
  }
}
