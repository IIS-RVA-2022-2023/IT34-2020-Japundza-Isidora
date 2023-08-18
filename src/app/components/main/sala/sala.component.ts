import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Bioskop } from 'src/app/models/bioskop';
import { Sala } from 'src/app/models/sala';
import { SalaService } from 'src/app/services/sala.service';
import { SalaDialogComponent } from '../../dialogs/sala-dialog/sala-dialog.component';

@Component({
  selector: 'app-sala',
  templateUrl: './sala.component.html',
  styleUrls: ['./sala.component.css'],
})
export class SalaComponent {
  dataSource!: MatTableDataSource<Sala>;
  displayedColumns = ['id', 'kapacitet', 'brojRedova', 'bioskop', 'actions'];
  subscription!: Subscription;

  parentSelectedSala!: Sala;

  //dijalog je ovo sto injekutujemo u konstruktoru a onaj dijalog sto smo kreirali je sadrzaj
  constructor(private salaService: SalaService, public dialog: MatDialog) {}

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData() {
    (this.subscription = this.salaService.getAllSalas().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
      console.log(data);
    })),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      };
  }

  public openDialog(
    flag: number,
    id?: number,
    kapacitet?: number,
    brojRedova?: number,
    bioskop?: Bioskop
  ): void {
    const dialogRef = this.dialog.open(SalaDialogComponent, {
      data: { id, kapacitet, brojRedova, bioskop },
    });
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe((result) => {
      if (result == 1) {
        this.loadData();
      }
    });
  }

  public selectRow(row: Sala) {
    this.parentSelectedSala = row;
    console.log(row);
  }

  public applyFilter(filter: any) {
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;
  }
}
