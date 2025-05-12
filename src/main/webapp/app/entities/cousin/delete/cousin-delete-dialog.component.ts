import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICousin } from '../cousin.model';
import { CousinService } from '../service/cousin.service';

@Component({
  standalone: true,
  templateUrl: './cousin-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CousinDeleteDialogComponent {
  cousin?: ICousin;

  protected cousinService = inject(CousinService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cousinService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
