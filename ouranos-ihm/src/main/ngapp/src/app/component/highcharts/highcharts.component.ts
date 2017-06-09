import { Component, OnInit, ViewChild } from '@angular/core';
import { MdRadioModule } from '@angular/material';
import { Observable } from 'rxjs/Rx';

import { ByPeriod } from '../../model/api/response/visibleSats/byPeriod/byPeriod';

@Component({
    selector: 'highcharts',
    templateUrl: './highcharts.component.html',
    styleUrls: ['./highcharts.component.css'],
    providers: []
})

export class HighchartsComponent implements OnInit {

    public currentChartOptions = {};
    public chartsOptions = [];
    public graphicChoises = ['barre', 'skyplot'];
    public favoritChoise: string;


    constructor() {
    }

    ngOnInit() {
        // do something
    }

    clearGraphe(): void {
        this.chartsOptions = [];
    }

    afficherGraphe(key: string, chartOptions: any): void {
        this.chartsOptions[key] = chartOptions;
        this.changeGraphe(key);
    }

    changeGraphe(key: string): void {
        this.currentChartOptions = this.chartsOptions[key];
        console.log(this.currentChartOptions);
    }

    displayGraphe(value: string): void {
        console.log(value);
        this.changeGraphe(value);
    }

}
