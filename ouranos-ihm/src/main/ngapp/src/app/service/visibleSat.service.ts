import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { HttpService } from './http.service';

import { ByPeriod } from '../model/api/response/visibleSats/byPeriod/byPeriod';
import { BySatellite } from '../model/api/response/visibleSats/bySatellite/bySatellite';
import { Parameters } from '../model/api/request/parameters';


@Injectable()
export class VisibleSatService {

    private defaultQuestion = '"latitude":"38.889139","longitude":"-77.049","altitude":"130.049","startDateOfMeasure":"1387666800","endDateOfMeasure":"1387753199","elevationMask":"15.0"';
    private httpService: HttpService;

    constructor(private http: Http) { 
         this.httpService = new HttpService(this.http);
    }

    getSateliteVisibleByPeriod(parameters : Parameters): Observable<ByPeriod> {

        let params = this.generateURLSearchParams(parameters);
        let sateliteVisible = this.httpService.httpGet("api/visibleSat/v01/byPeriod", params);

        return sateliteVisible;
    }

    getSateliteVisibleBySatellite(parameters : Parameters): Observable<BySatellite> {

        let params = this.generateURLSearchParams(parameters);
        params.set("requete", JSON.stringify(parameters));
        let sateliteVisible = this.httpService.httpGet("api/visibleSat/v01/bySatellite", params);

        return sateliteVisible;
    }

    generateURLSearchParams(parameters : Parameters): URLSearchParams {
        let params = new URLSearchParams();

        params.set("startDateOfMeasure", parameters.startDateOfMeasure);
        params.set("endDateOfMeasure", parameters.endDateOfMeasure);
        params.set("elevationMask", parameters.elevationMask.toString());
        params.set("longitude", parameters.longitude.toString());
        params.set("latitude", parameters.latitude.toString());
        params.set("altitude", parameters.altitude.toString());

        return params;
    }
}