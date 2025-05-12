import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAndrogene } from '../androgene.model';
import { AndrogeneService } from '../service/androgene.service';

@Component({
  standalone: true,
  templateUrl: './androgene-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AndrogeneDeleteDialogComponent {
  androgene?: IAndrogene;

  protected androgeneService = inject(AndrogeneService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.androgeneService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
