import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICytogeneticien } from '../cytogeneticien.model';
import { CytogeneticienService } from '../service/cytogeneticien.service';

@Component({
  standalone: true,
  templateUrl: './cytogeneticien-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CytogeneticienDeleteDialogComponent {
  cytogeneticien?: ICytogeneticien;

  protected cytogeneticienService = inject(CytogeneticienService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cytogeneticienService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
