import { Coordinate } from '../coordinate';

export class Position {
    constructor(
        public epochHeader: Date,
        public sphericalCoordinate: Coordinate
    ) { }
}