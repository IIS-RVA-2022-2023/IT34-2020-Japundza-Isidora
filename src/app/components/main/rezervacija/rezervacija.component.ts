import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Film } from 'src/app/models/film';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Rezervacija } from 'src/app/models/rezervacija';
import { RezervacijaService } from 'src/app/services/rezervacija.service';
import { RezervacijaDialogComponent } from '../../dialogs/rezervacija-dialog/rezervacija-dialog.component';
import { Sala } from 'src/app/models/sala';

@Component({
  selector: 'app-rezervacija',
  templateUrl: './rezervacija.component.html',
  styleUrls: ['./rezervacija.component.css'],
})
export class RezervacijaComponent implements OnChanges {
  dataSource!: MatTableDataSource<Rezervacija>;
  displayedColumns = [
    'id',
    'brojOsoba',
    'cenaKarte',
    'datum',
    'placeno',
    'film',
    'actions',
  ];
  subscription!: Subscription;

  @Input() childSelectedSala!: Sala;

  constructor(
    private rezervacijaService: RezervacijaService,
    public dialog: MatDialog
  ) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (this.childSelectedSala.id) {
      this.loadData();
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  // LOAD DATA
  public loadData() {
    (this.subscription = this.rezervacijaService
      .getRezervacijeForSala(this.childSelectedSala.id)
      .subscribe((data) => {
        this.dataSource = new MatTableDataSource(data);
        console.log(data);
      })),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      };
  }

  // OPEN DIALOG
  public openDialog(
    flag: number,
    id?: number,
    brojOsoba?: number,
    cenaKarte?: number,
    datum?: Date,
    placeno?: boolean,
    film?: Film
  ): void {
    const dialogRef = this.dialog.open(RezervacijaDialogComponent, {
      data: { id, brojOsoba, cenaKarte, datum, placeno, film },
    });
    dialogRef.componentInstance.flag = flag;
    dialogRef.componentInstance.data.sala = this.childSelectedSala;
    dialogRef.afterClosed().subscribe((result) => {
      if (result == 1) {
        this.loadData();
      }
    });
  }
}
