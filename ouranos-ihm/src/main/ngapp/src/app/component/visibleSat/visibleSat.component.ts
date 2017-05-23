import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { TemporalAndSpatialComponent } from '../temporalAndSpatial/temporalAndSpatial.component';
import { LlaComponent } from '../lla/lla.component';
import { HighchartsComponent } from '../highcharts/highcharts.component';

import { VisibleSatService } from '../../service/visibleSat.service';
import { VisibleSats } from '../../model/VisibleSats';
import { Parameters } from '../../model/Parameters';

@Component({
    selector: 'visibleSat',
    templateUrl: './visibleSat.component.html',
    styleUrls: ['./visibleSat.component.css'],
    providers: [VisibleSatService]
})

export class VisibleSatComponent implements OnInit {

    @ViewChild(TemporalAndSpatialComponent) tempAndSpat: TemporalAndSpatialComponent;
    @ViewChild(LlaComponent) LlaComponent: LlaComponent;
    @ViewChild(HighchartsComponent) highchartsComponent: HighchartsComponent;

    private visibleSats: VisibleSats;
    public apiRequest: Parameters;

    constructor(private visibleSatService: VisibleSatService) {
        this.apiRequest = new Parameters();
    }

    ngOnInit() {
        // do something
    }

    onClickMe() {

        this.apiRequest.elevationMask = this.tempAndSpat.getElevationMask();
        this.apiRequest.startDateOfMeasure = this.tempAndSpat.getDateStartMeasureISO();
        this.apiRequest.endDateOfMeasure = this.tempAndSpat.getDateEndMeasureISO();
        this.apiRequest.groundStation.longitude = this.LlaComponent.getLongitude();
        this.apiRequest.groundStation.latitude = this.LlaComponent.getLatitude();
        this.apiRequest.groundStation.altitude = this.LlaComponent.getAltitude();

        console.log(this.apiRequest);

        this.getSateliteVisible();
    }

    getSateliteVisible(): void {
        this.visibleSatService.getSateliteVisible(this.apiRequest).subscribe(
            visibleSats => {
                // Emit list event
                this.visibleSats = visibleSats;
                this.highchartsComponent.afficherGraphe(this.visibleSats);
            },
            err => {
                // Log errors if any
                console.log(err);
            });
    }
}
