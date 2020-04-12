import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { TemporalAndSpatialComponent } from '../temporalAndSpatial/temporalAndSpatial.component';
import { LlaComponent } from '../lla/lla.component';
import { HighchartsComponent } from '../highcharts/highcharts.component';

import { VisibleSatService } from '../../service/visibleSat.service';
import { ByPeriod } from '../../model/api/response/visibleSats/byPeriod/byPeriod';
import { Parameters } from '../../model/api/request/parameters';

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

    private byPeriod: ByPeriod;
    public apiRequest: Parameters;

    constructor(private visibleSatService: VisibleSatService) {
        this.apiRequest = new Parameters();
    }

    ngOnInit() {
        // do something
    }

    onClickMe() {

        this.highchartsComponent.clearGraphe();

        this.apiRequest.elevationMask = this.tempAndSpat.getElevationMask();
        this.apiRequest.startDateOfMeasure = this.tempAndSpat.getTimestampStartMeasureSecond();
        this.apiRequest.endDateOfMeasure = this.tempAndSpat.getTimestampEndMeasureSecond();
        this.apiRequest.longitude = this.LlaComponent.getLongitude();
        this.apiRequest.latitude = this.LlaComponent.getLatitude();
        this.apiRequest.altitude = this.LlaComponent.getAltitude();

        this.getSateliteVisibleByPeriod();

    }

    getSateliteVisibleByPeriod(): void {
        console.log(this.apiRequest);
        this.visibleSatService.getSateliteVisibleByPeriod(this.apiRequest).subscribe(
            visibleSats => {
                // Emit list event
                this.byPeriod = visibleSats;

                this.displaySkyPlot();
                this.displayVisibleByPeriod();
            },
            err => {
                // Log errors if any
                console.log(err);
            });
    }

    displayVisibleByPeriod(): void {

        let ouranosCategories = [];
        let ouranosSeries = [];

        for (let VisibleSat of this.byPeriod.satellitesVisible) {
            ouranosCategories.push(VisibleSat.epochHeader);
            ouranosSeries.push(VisibleSat.satellites.length);
        }

        let chartOptions = {
            chart: {
                type: 'column'
            },
            title: {
                text: 'Visibility'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: ouranosCategories, // Times
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Number of satelites'
                }
            },
            series: [{
                name: 'Satelite(s)',
                data: ouranosSeries
            }]
        };

        this.highchartsComponent.afficherGraphe("barre", chartOptions);
    }

    displaySkyPlot(): void {
        let chartOptions = {
            chart: {
                polar: true
            },
            title: {
                text: 'Highcharts Polar Chart'
            },
            pane: {
                startAngle: 0,
                endAngle: 360
            },
            xAxis: {
                tickInterval: 45,
                min: 0,
                max: 360,
                labels: {
                    formatter: function () {
                        return this.value + '°';
                    }
                }
            },
            yAxis: {
                min: 0
            },
            plotOptions: {
                series: {
                    pointStart: 0,
                    pointInterval: 45
                },
                column: {
                    pointPadding: 0,
                    groupPadding: 0
                }
            },
            series: [{
                type: 'column',
                name: 'Column',
                data: [8, 7, 6, 5, 4, 3, 2, 1],
                pointPlacement: 'between'
            }, {
                type: 'line',
                name: 'Line',
                data: [1, 2, 3, 4, 5, 6, 7, 8]
            }]
        };

        this.highchartsComponent.afficherGraphe("skyplot", chartOptions);
    }
}
