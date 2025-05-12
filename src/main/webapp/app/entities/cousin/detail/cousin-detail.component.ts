import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { ICousin } from '../cousin.model';

@Component({
  standalone: true,
  selector: 'jhi-cousin-detail',
  templateUrl: './cousin-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CousinDetailComponent {
  cousin = input<ICousin | null>(null);

  previousState(): void {
    window.history.back();
  }
}
