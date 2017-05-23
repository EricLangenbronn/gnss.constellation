import { GroundStation } from './groundStation';

export class Parameters {

    public startDateOfMeasure : string;
    public endDateOfMeasure : String;
    public elevationMask : number;
    public groundStation : GroundStation;

    constructor() {
        this.startDateOfMeasure = "";
        this.endDateOfMeasure = "";
        this.elevationMask = 15.0;
        this.groundStation = new GroundStation();
    }
}