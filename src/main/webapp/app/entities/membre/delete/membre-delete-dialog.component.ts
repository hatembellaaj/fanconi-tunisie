import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IMembre } from '../membre.model';
import { MembreService } from '../service/membre.service';

@Component({
  standalone: true,
  templateUrl: './membre-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class MembreDeleteDialogComponent {
  membre?: IMembre;

  protected membreService = inject(MembreService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.membreService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
