import { Sala } from './sala';
import { Film } from './film';

export class Rezervacija {
  id!: number;
  brojOsoba!: number;
  cenaKarte!: number;
  datum!: Date;
  placeno!: boolean;
  film!: Film;
  sala!: Sala;
}
