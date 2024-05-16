import { Routes } from '@angular/router';
import { MenetlusTableComponent } from './components/menetlus/menetlus-table/menetlus-table.component';
import { NewMenetlusComponent } from './components/menetlus/new-menetlus/new-menetlus.component';

export const routes: Routes = [
  { path: '', component: MenetlusTableComponent },
  { path: 'new', component: NewMenetlusComponent },
  { path: '**',redirectTo: '/', pathMatch: 'full' }
];
