import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Film } from 'src/app/models/film';
import { Rezervacija } from 'src/app/models/rezervacija';
import { FilmService } from 'src/app/services/film.service';
import { RezervacijaService } from 'src/app/services/rezervacija.service';

//import { Sala } from 'src/app/models/sala';
//import { SalaService } from 'src/app/services/sala.service';

@Component({
  selector: 'app-rezervacija-dialog',
  templateUrl: './rezervacija-dialog.component.html',
  styleUrls: ['./rezervacija-dialog.component.css'],
})
export class RezervacijaDialogComponent {
  flag!: number;
  filmovi!: Film[];
  //sale!: Sala[]; // dodato

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Rezervacija>,
    @Inject(MAT_DIALOG_DATA) public data: Rezervacija,
    public rezervacijaService: RezervacijaService,
    public filmService: FilmService
  ) //public salaService: SalaService // dodato
  {}

  ngOnInit(): void {
    this.filmService.getAllFilms().subscribe((data) => {
      this.filmovi = data;
    });
  }

  /*ngOnInit(): void {
    this.salaService.getAllSalas().subscribe((data) => {
      this.sale = data;
    });
  }*/

  public add(): void {
    this.rezervacijaService.addRezervacija(this.data).subscribe(() => {
      this.snackBar.open('Rezervacija je uspesno dodata!', 'Ok', {
        duration: 4500,
      });
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greska', 'Ok', { duration: 2500 });
      };
  }

  public update(): void {
    this.rezervacijaService.updateRezervacija(this.data).subscribe(() => {
      this.snackBar.open(
        'Rezervacija sa ID: ' + this.data.id + ' je uspesno izmenjena!',
        'Ok',
        { duration: 4500 }
      );
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greska', 'Ok', { duration: 2500 });
      };
  }

  public delete(): void {
    this.rezervacijaService.deleteRezervacija(this.data.id).subscribe(() => {
      this.snackBar.open('Rezervacija je izbrisana!', 'Ok', { duration: 4500 });
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greska', 'Ok', { duration: 2500 });
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmena!', 'Ok', { duration: 2500 });
  }

  public compare(a: any, b: any) {
    return a.id == b.id;
  }
}
