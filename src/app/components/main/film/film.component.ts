import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Film } from 'src/app/models/film';
import { FilmService } from 'src/app/services/film.service';
import { FilmDialogComponent } from '../../dialogs/film-dialog/film-dialog.component';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-film',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.css'],
})
export class FilmComponent implements OnInit, OnDestroy {
  dataSource!: MatTableDataSource<Film>;
  displayedColumns = [
    'id',
    'naziv',
    'recenzija',
    'trajanje',
    'zanr',
    'actions',
  ];
  subscription!: Subscription;

  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  //dijalog je ovo sto injekutujemo u konstruktoru a onaj dijalog sto smo kreirali je sadrzaj
  constructor(private filmService: FilmService, public dialog: MatDialog) {}

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData() {
    (this.subscription = this.filmService.getAllFilms().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    })),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      };
  }

  // iz html-a prosledjujemo ove podatke dijalogu
  public openDialog(
    flag: number,
    id?: number,
    naziv?: string,
    recenzija?: number,
    trajanje?: number,
    zanr?: string
  ): void {
    const dialogRef = this.dialog.open(FilmDialogComponent, {
      data: { id, naziv, recenzija, trajanje, zanr },
    });
    // otvara modalni dijalog odgovarajuce komponente
    // vracamo instancu kreirane komponente dialoga
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe((result) => {
      if (result == 1) {
        // uspesno
        this.loadData(); // ponovo ucitaj podatke
      }
    });
  }

  public applyFilter(filter: any) {
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;
  }
}
