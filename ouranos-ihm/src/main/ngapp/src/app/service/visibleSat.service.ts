import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { HttpService } from './http.service';

import { VisibleSats } from '../model/VisibleSats';
import { Parameters } from '../model/parameters';


@Injectable()
export class VisibleSatService {

    private defaultQuestion = '{"groundStation":{"latitude":"38.889139","longitude":"-77.049","altitude":"130.049"},"startDateOfMeasure":"2013-12-22T00:00:00","endDateOfMeasure":"2013-12-22T23:45:00","elevationMask":"15.0"}';
    private httpService: HttpService;

    constructor(private http: Http) { 
         this.httpService = new HttpService(this.http);
    }

    getSateliteVisible(parameters : Parameters): Observable<VisibleSats> {

        let params = new URLSearchParams();
        params.set("requete", JSON.stringify(parameters));
        let sateliteVisible = this.httpService.httpGet("ouranos/api/visibleSat/v01", params);

        return sateliteVisible;
    }
}