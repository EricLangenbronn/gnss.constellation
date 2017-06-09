import { Coordinate } from '../coordinate';

export class Satellite {
    constructor(
        public id: string,
        public sphericalCoordinate: Coordinate
    ) { }
}