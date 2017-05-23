import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs/Rx';


@Component({
    selector: 'lla',
    templateUrl: './lla.component.html',
    styleUrls: ['./lla.component.css'],
    providers: []
})

export class LlaComponent implements OnInit {

    private longitude: number;
    private latitude: number;
    private altitude: number;

    constructor() {
        this.longitude = 7.74553;
        this.latitude = 48.58392;
        this.altitude = 147.000;
    }

    ngOnInit() {
        // do something
    }

    getLongitude(): number {
        return this.longitude;
    }

    getLatitude(): number {
        return this.latitude;
    }

    getAltitude(): number {
        return this.altitude;
    }
}