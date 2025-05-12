import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IScientifique } from '../scientifique.model';
import { ScientifiqueService } from '../service/scientifique.service';

@Component({
  standalone: true,
  templateUrl: './scientifique-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ScientifiqueDeleteDialogComponent {
  scientifique?: IScientifique;

  protected scientifiqueService = inject(ScientifiqueService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.scientifiqueService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
