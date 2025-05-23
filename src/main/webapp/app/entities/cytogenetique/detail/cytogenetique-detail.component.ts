import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { ICytogenetique } from '../cytogenetique.model';

@Component({
  standalone: true,
  selector: 'jhi-cytogenetique-detail',
  templateUrl: './cytogenetique-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CytogenetiqueDetailComponent {
  cytogenetique = input<ICytogenetique | null>(null);

  previousState(): void {
    window.history.back();
  }
}
