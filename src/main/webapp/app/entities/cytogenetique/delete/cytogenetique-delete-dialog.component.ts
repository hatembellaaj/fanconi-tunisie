import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICytogenetique } from '../cytogenetique.model';
import { CytogenetiqueService } from '../service/cytogenetique.service';

@Component({
  standalone: true,
  templateUrl: './cytogenetique-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CytogenetiqueDeleteDialogComponent {
  cytogenetique?: ICytogenetique;

  protected cytogenetiqueService = inject(CytogenetiqueService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cytogenetiqueService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
