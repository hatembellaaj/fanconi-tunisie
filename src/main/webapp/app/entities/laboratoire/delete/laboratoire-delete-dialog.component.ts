import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ILaboratoire } from '../laboratoire.model';
import { LaboratoireService } from '../service/laboratoire.service';

@Component({
  standalone: true,
  templateUrl: './laboratoire-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class LaboratoireDeleteDialogComponent {
  laboratoire?: ILaboratoire;

  protected laboratoireService = inject(LaboratoireService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.laboratoireService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
