import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FILM_URL } from '../constants';
import { Film } from '../models/film';

@Injectable({
  providedIn: 'root',
})
export class FilmService {
  constructor(private httpClient: HttpClient) {}

  public getAllFilms(): Observable<any> {
    return this.httpClient.get(`${FILM_URL}`);
  }

  public addFilm(film: Film): Observable<any> {
    return this.httpClient.post(`${FILM_URL}`, film);
  }

  public updateFilm(film: Film): Observable<any> {
    return this.httpClient.put(`${FILM_URL}/${film.id}`, film);
  }

  public deleteFilm(id: number): Observable<any> {
    return this.httpClient.delete(`${FILM_URL}/${id}`);
  }
}
