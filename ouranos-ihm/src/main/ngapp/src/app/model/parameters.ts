import { GroundStation } from './groundStation';

export class Parameters {

    public startDateOfMeasure : Date;
    public endDateOfMeasure : Date;
    public elevationMask : number;
    public groundStation : GroundStation;

    constructor() {
        this.startDateOfMeasure = new Date("2013-12-22T01:00:00");
        this.endDateOfMeasure = new Date("2013-12-22T23:45:00");
        this.elevationMask = 15.0;
        this.groundStation = new GroundStation();
    }
}