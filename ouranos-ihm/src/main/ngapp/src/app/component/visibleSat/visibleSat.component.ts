import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { VisibleSatService } from '../../service/visibleSat.service';
import { VisibleSat } from '../../model/visibleSat';

@Component({
    selector: 'visibleSat',
    templateUrl: './visibleSat.component.html',
    styleUrls: ['./visibleSat.component.css'],
    providers: [VisibleSatService]
})

export class VisibleSatComponent implements OnInit {

    visibleSats: VisibleSat;

    constructor(private visibleSatService: VisibleSatService) {
    }

    ngOnInit() {
        this.getSateliteVisible();
    }


    getSateliteVisible(): void {
        this.visibleSatService.getSateliteVisible().subscribe(
            visibleSats => {
                // Emit list event
                this.visibleSats = visibleSats;
                console.log("component");
                console.log(visibleSats);
            },
            err => {
                // Log errors if any
                console.log(err);
            });;
    }
}
