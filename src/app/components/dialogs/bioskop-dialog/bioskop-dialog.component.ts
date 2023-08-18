import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Bioskop } from 'src/app/models/bioskop';
import { BioskopService } from 'src/app/services/bioskop.service';

@Component({
  selector: 'app-bioskop-dialog',
  templateUrl: './bioskop-dialog.component.html',
  styleUrls: ['./bioskop-dialog.component.css'],
})
export class BioskopDialogComponent {
  // dependency inj se radi pomocu konstruktora u typescript-u
  flag!: number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Bioskop>,
    @Inject(MAT_DIALOG_DATA) public data: Bioskop,
    public bioskopService: BioskopService
  ) {}

  public add(): void {
    this.bioskopService.addBioskop(this.data).subscribe(() => {
      this.snackBar.open(
        'Bioskop sa nazivom: ' + this.data.naziv + ' je uspesno dodat!',
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
    this.bioskopService.updateBioskop(this.data).subscribe(() => {
      this.snackBar.open(
        'Bioskop sa ID: ' + this.data.id + ' je uspesno izmenjen!',
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
    this.bioskopService.deleteBioskop(this.data.id).subscribe(() => {
      this.snackBar.open('Bioskop je izbrisan!', 'OK', { duration: 4500 });
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
