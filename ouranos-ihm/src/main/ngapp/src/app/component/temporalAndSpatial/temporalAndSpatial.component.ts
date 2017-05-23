import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { MdDatepickerModule, MdNativeDateModule, MdInputModule, MdDatepicker } from '@angular/material';


@Component({
    selector: 'temporalAndSpatial',
    templateUrl: './temporalAndSpatial.component.html',
    styleUrls: ['./temporalAndSpatial.component.css'],
    providers: []
})


// https://blog.netapsys.fr/construire-des-dates-exprimees-en-utc-via-javascript-2/
export class TemporalAndSpatialComponent implements OnInit {

    @ViewChild(MdDatepicker) datepickerStartTime: MdDatepicker<Date>;
    @ViewChild(MdDatepicker) datepickerEndTime: MdDatepicker<Date>;

    private elevationMask: number;
    private startDate: Date;
    private endDate: Date;

    constructor() {
        this.elevationMask = 15.0;
        this.startDate = new Date(Date.UTC(2015, 1, 1, 0, 0, 0, 0));
        this.endDate = new Date(Date.UTC(2015, 1, 1, 23, 0, 0, 0));
    }

    ngOnInit() {
        // do something
    }

    getElevationMask(): number {
        return this.elevationMask;
    }

    getDateStartMeasureISO(): string {
        //return this.datepickerStartTime._selected.toISOString();
        return this.startDate.toISOString();
    }

    getDateEndMeasureISO(): string {
        //return this.datepickerEndTime._selected.toISOString();
        return this.endDate.toISOString();
    }
}