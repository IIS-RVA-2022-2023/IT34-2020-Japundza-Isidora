import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BIOSKOP_URL } from '../constants';
import { Bioskop } from '../models/bioskop';

@Injectable({
  providedIn: 'root',
})
export class BioskopService {
  constructor(private httpClient: HttpClient) {}
  //u konstruktoru se vrsi dependency injection

  public getAllBioskops(): Observable<any> {
    return this.httpClient.get(`${BIOSKOP_URL}`); //moze i samo (BIOSKOP_URL)
  }

  public addBioskop(bioskop: Bioskop): Observable<any> {
    return this.httpClient.post(`${BIOSKOP_URL}`, bioskop);
  }

  public updateBioskop(bioskop: Bioskop): Observable<any> {
    return this.httpClient.put(`${BIOSKOP_URL}/${bioskop.id}`, bioskop);
  }

  public deleteBioskop(id: number): Observable<any> {
    return this.httpClient.delete(`${BIOSKOP_URL}/${id}`);
  }
}
