import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { Ng2HighchartsModule } from 'ng2-highcharts';
import { MdDatepickerModule, MdNativeDateModule, MdInputModule } from '@angular/material';

import { AppComponent } from './app.component';
import { VisibleSatComponent } from './component/visibleSat/visibleSat.component';
import { TemporalAndSpatialComponent } from './component/temporalAndSpatial/temporalAndSpatial.component';
import { LlaComponent } from './component/lla/lla.component';
import { HighchartsComponent } from './component/highcharts/highcharts.component';

@NgModule({
  declarations: [
    AppComponent,
    VisibleSatComponent,
    TemporalAndSpatialComponent,
    LlaComponent,
    HighchartsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    Ng2HighchartsModule,
    MdDatepickerModule,
    MdNativeDateModule,
    MdInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
