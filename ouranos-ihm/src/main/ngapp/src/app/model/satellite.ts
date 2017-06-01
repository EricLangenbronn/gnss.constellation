import { Position } from './position';

export class Satellite {
    constructor(
        public id: string,
        public sphericalCoordinate: Position
    ) { }
}