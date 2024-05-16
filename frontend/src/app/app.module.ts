import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MenetlusTableComponent } from './components/menetlus/menetlus-table/menetlus-table.component';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NewMenetlusComponent } from './components/menetlus/new-menetlus/new-menetlus.component';
import { NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonsModule } from 'ngx-bootstrap/buttons';

@NgModule({
  declarations: [
    MenetlusTableComponent,
    NewMenetlusComponent
  ],
  imports: [
    AppComponent,
    BrowserAnimationsModule,
    BrowserModule,
    ButtonsModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    HttpClientXsrfModule,
    NgbModalModule,
    NgbModule,
    ReactiveFormsModule,
  ],
  providers: [
    HttpClientModule
  ]
})
export class AppModule {}
