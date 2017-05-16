import { VisibleSat } from './visibleSat';


/* MDR, les objects typeScript ne servent à rien c'est juste du sucre syntaxique
   si ton json derrière ne possede pas exactement le même nom et le même types de
   champs il te renvoie systematiquement undefined :), la blague
 */
export class VisibleSats {
    constructor(
        public satellitesVisible: VisibleSat[]
    ) { }
}