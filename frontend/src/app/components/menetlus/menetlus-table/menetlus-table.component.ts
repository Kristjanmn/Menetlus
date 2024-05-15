import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenetlusService } from '../../../service/menetlus.service';
import { Menetlus } from '../../../model/menetlus';

@Component({
  selector: 'menetlus-table',
  templateUrl: './menetlus-table.component.html'
})
export class MenetlusTableComponent implements OnInit {
  menetlusList?: Menetlus[];

  constructor(
    private router: Router,
    private service: MenetlusService
  ) {
  }
  ngOnInit(): void {
    this.getData();
  }

  getData(): void {
    this.service.getAll()
      .subscribe(response => {
        this.menetlusList = response;
      });
  }
}
