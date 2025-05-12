import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IHopital } from '../hopital.model';
import { HopitalService } from '../service/hopital.service';

@Component({
  standalone: true,
  templateUrl: './hopital-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class HopitalDeleteDialogComponent {
  hopital?: IHopital;

  protected hopitalService = inject(HopitalService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.hopitalService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
