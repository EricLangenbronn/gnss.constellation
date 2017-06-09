import { Satellite } from './satellite';

export class Period {
    constructor(
        public epochHeader: Date,
        public satellites: Satellite[]
    ) { }
}