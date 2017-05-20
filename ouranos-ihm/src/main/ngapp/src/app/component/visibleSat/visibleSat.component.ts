import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';

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

    private visibleSats: VisibleSats;
    public ouranosCategories = [];
    public ouranosSeries = [];
    public chartOptions = {};
    public parameters: Parameters;

    constructor(private visibleSatService: VisibleSatService) { 
        this.parameters = new Parameters();
    }

    ngOnInit() {
       // do something
    }

    onClickMe() {
        console.log(this.parameters);
         this.getSateliteVisible();
    }

    getSateliteVisible(): void {
        this.visibleSatService.getSateliteVisible(this.parameters).subscribe(
            visibleSats => {
                // Emit list event
                this.visibleSats = visibleSats;
                this.afficherGraphe();
            },
            err => {
                // Log errors if any
                console.log(err);
            });
    }

    afficherGraphe() {
        console.log("afficherGraphe");
        console.log(this.visibleSats);
        for (let VisibleSat of this.visibleSats.satellitesVisible) {
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
