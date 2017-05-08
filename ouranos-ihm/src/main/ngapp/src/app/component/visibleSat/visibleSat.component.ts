import { Component, OnInit } from '@angular/core';

import { VisibleSatService } from '../../service/visibleSat.service';

@Component({
    selector: 'visibleSat',
    templateUrl: './visibleSat.component.html',
    styleUrls: ['./visibleSat.component.css'],
    providers: [VisibleSatService]
})

export class VisibleSatComponent implements OnInit {

    constructor(private visibleSatService: VisibleSatService) {
    }

    ngOnInit() {
        this.getSateliteVisible();
    }


    getSateliteVisible(): void {
        this.visibleSatService.getSateliteVisible();
    }
}
