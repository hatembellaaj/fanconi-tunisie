import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFrere } from '../frere.model';
import { FrereService } from '../service/frere.service';

@Component({
  standalone: true,
  templateUrl: './frere-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FrereDeleteDialogComponent {
  frere?: IFrere;

  protected frereService = inject(FrereService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.frereService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
