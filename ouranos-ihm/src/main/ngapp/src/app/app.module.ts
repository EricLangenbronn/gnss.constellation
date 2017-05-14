import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { Ng2HighchartsModule } from 'ng2-highcharts';

import { AppComponent } from './app.component';
import { VisibleSatComponent } from './component/visibleSat/visibleSat.component';

@NgModule({
  declarations: [
    AppComponent,
    VisibleSatComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    Ng2HighchartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
