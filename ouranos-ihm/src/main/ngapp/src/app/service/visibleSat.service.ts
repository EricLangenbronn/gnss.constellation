import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Rx';

@Injectable()
export class VisibleSatService {

    private question = '{"groundStation":{"latitude":"38.889139","longitude":"-77.049","altitude":"130.049"},"startDateOfMeasure":"2013-12-22T00:00:00","endDateOfMeasure":"2013-12-22T23:45:00","elevationMask":"15.0"}';
    private header = new Headers([
        'Accept', 'application/json',
        'Content-Type', 'application/json'
    ]);
    private baseUrl = "http://127.0.0.1:8080/ouranos-war";

    constructor(private http: Http) { }

    getSateliteVisible(): void {
        console.log("satelite visible");
        console.log(`${this.baseUrl}/ouranos/api/v01/visibleSat`);
        console.log(this.question);
        console.log(this.header);
        this.http.post(`${this.baseUrl}/ouranos/api/v01/visibleSat`, this.question, this.header)
            .map((res: Response) => res.json()) // ...and calling .json() on the response to return data
            .catch((error: any) => Observable.throw(error.json().error || 'Server error')); //...errors if any
    }
}