import { Component } from '@angular/core';
import { Menetlus } from '../../../model/menetlus';
import { MenetlusService } from '../../../service/menetlus.service';
import { Router } from '@angular/router';

@Component({
  selector: 'new-menetlus',
  templateUrl: './new-menetlus.component.html'
})
export class NewMenetlusComponent {
  menetlus: Menetlus = new Menetlus('', 0, '', '');

  constructor(
    private service: MenetlusService,
    private router: Router
  ) {
  }

  onSubmit() {
    if (this.service.validateForm(this.menetlus)) {
      this.service.saveMenetlus(this.menetlus)
        .subscribe(response => {
          if (response.success) this.goBack();
          else window.alert(response.message);
        });
    }
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
