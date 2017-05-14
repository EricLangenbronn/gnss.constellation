import { Satellite } from './satellite';

export class VisibleSat {
    constructor(
        public epochHeader: Date,
        public satelites: Satellite[]
    ) { }
}