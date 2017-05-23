import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { VisibleSats } from '../../model/VisibleSats';

@Component({
    selector: 'highcharts',
    templateUrl: './highcharts.component.html',
    styleUrls: ['./highcharts.component.css'],
    providers: []
})

export class HighchartsComponent implements OnInit {


    public ouranosCategories = [];
    public ouranosSeries = [];
    public chartOptions = {};

    constructor() {
    }

    ngOnInit() {
        // do something
    }

    afficherGraphe(visibleSats: VisibleSats): void {
        for (let VisibleSat of visibleSats.satellitesVisible) {
            this.ouranosCategories.push(VisibleSat.epochHeader);
            this.ouranosSeries.push(VisibleSat.satellites.length);
        }

        this.chartOptions = {
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
                categories: this.ouranosCategories, // Times
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
                data: this.ouranosSeries
            }]
        };
    }

}
