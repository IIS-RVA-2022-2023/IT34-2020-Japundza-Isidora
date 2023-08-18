import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Film } from 'src/app/models/film';
import { FilmService } from 'src/app/services/film.service';

@Component({
  selector: 'app-film-dialog',
  templateUrl: './film-dialog.component.html',
  styleUrls: ['./film-dialog.component.css'],
})
export class FilmDialogComponent {
  // dependency inj se radi pomocu konstruktora u ts
  flag!: number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Film>,
    @Inject(MAT_DIALOG_DATA) public data: Film,
    public filmService: FilmService
  ) {}

  public add(): void {
    this.filmService.addFilm(this.data).subscribe(() => {
      this.snackBar.open(
        'Film sa nazivom: ' + this.data.naziv + ' je uspesno dodat!',
        'OK',
        { duration: 4500 }
      );
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greska', 'OK', { duration: 2500 });
      };
  }

  public update(): void {
    this.filmService.updateFilm(this.data).subscribe(() => {
      this.snackBar.open(
        'Film sa ID: ' + this.data.id + ' je uspesno izmenjen!',
        'OK',
        { duration: 4500 }
      );
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greska', 'OK', { duration: 2500 });
      };
  }

  public delete(): void {
    this.filmService.deleteFilm(this.data.id).subscribe(() => {
      this.snackBar.open('Film je izbrisan!', 'OK', { duration: 4500 });
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greska', 'OK', { duration: 2500 });
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmena', 'Ok', { duration: 2500 });
  }
}
