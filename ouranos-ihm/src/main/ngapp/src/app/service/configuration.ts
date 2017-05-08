import { Injectable } from '@angular/core';

@Injectable()
export class Configuration {
    public Server: string = 'http://localhost:8080/ouranos.war';
    public ApiUrl: string = '/ouranos/api/';
    public ServerWithApiUrl = this.Server + this.ApiUrl;
}