import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import {environment} from '../../environments/environment';

@Injectable()
export class HttpService {

    private baseUrl = `${environment.urlServer}`;    

    constructor(private http: Http) { }


    httpGet(url: string, params: URLSearchParams): Observable<any> {

        const headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let requestOptions = new RequestOptions({ search: params, headers : headers});
        let request = this.http.get(`${this.baseUrl}/${url}`, requestOptions)
            .map((res: Response) => res.json()) // ...and calling .json() on the response to return data
            .catch(this.handleError);

        return request;
    }

    private handleError(error: Response | any) {
        // In a real world app, you might use a remote logging infrastructure
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }

}