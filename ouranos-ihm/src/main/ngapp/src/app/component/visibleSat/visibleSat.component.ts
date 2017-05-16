import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { VisibleSatService } from '../../service/visibleSat.service';
import { VisibleSats } from '../../model/VisibleSats';

@Component({
    selector: 'visibleSat',
    templateUrl: './visibleSat.component.html',
    styleUrls: ['./visibleSat.component.css'],
    providers: [VisibleSatService]
})

export class VisibleSatComponent implements OnInit {

    private visibleSats: VisibleSats;
    public ouranosCategories = [];
    public ouranosSeries = [];
    public chartOptions = {};

    constructor(private visibleSatService: VisibleSatService) { }

    ngOnInit() {
        this.getSateliteVisible();
    }


    getSateliteVisible(): void {
        this.visibleSatService.getSateliteVisible().subscribe(
            visibleSats => {
                // Emit list event
                this.visibleSats = visibleSats;
                console.log(visibleSats);
                this.afficherGraphe();
            },
            err => {
                // Log errors if any
                console.log(err);
            });
    }

    afficherGraphe() {
        console.log("afficherGraphe");
        for (let VisibleSat of this.visibleSats.satellitesVisible) {
            console.log(VisibleSat);
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

        console.log(this.chartOptions);
    }

}
