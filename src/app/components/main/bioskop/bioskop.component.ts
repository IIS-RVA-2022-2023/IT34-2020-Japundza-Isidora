import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Bioskop } from 'src/app/models/bioskop';
import { BioskopService } from 'src/app/services/bioskop.service';
import { BioskopDialogComponent } from '../../dialogs/bioskop-dialog/bioskop-dialog.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-bioskop',
  templateUrl: './bioskop.component.html',
  styleUrls: ['./bioskop.component.css'],
})
export class BioskopComponent implements OnInit, OnDestroy {
  dataSource!: MatTableDataSource<Bioskop>;
  displayedColumns = ['id', 'naziv', 'adresa', 'actions'];
  subscription!: Subscription;

  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(
    private bioskopService: BioskopService,
    public dialog: MatDialog
  ) {}

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  // LOAD DATA
  public loadData() {
    (this.subscription = this.bioskopService
      .getAllBioskops()
      .subscribe((data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      })),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      };
  }

  // OPEN DIALOG
  public openDialog(
    flag: number,
    id?: number,
    naziv?: string,
    adresa?: string
  ): void {
    const dialogRef = this.dialog.open(BioskopDialogComponent, {
      data: { id, naziv, adresa },
    });
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe((result) => {
      if (result == 1) {
        this.loadData();
      }
    });
  }

  // APPLY FILTER
  public applyFilter(filter: any) {
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;
  }
}
