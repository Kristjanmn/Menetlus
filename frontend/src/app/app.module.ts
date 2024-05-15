import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { MenetlusTableComponent } from './components/menetlus/menetlus-table/menetlus-table.component';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NewMenetlusComponent } from './components/menetlus/new-menetlus/new-menetlus.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [
    AppComponent,
    BrowserModule,
    CommonModule,
    HttpClientModule,
    HttpClientXsrfModule,
    NgbModule,
    ReactiveFormsModule,
  ],
  declarations: [
    MenetlusTableComponent,
    NewMenetlusComponent
  ],
  providers: [
    HttpClientModule
  ]
})
export class AppModule {}
