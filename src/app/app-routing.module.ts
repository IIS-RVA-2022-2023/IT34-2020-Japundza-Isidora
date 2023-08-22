import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FilmComponent } from './components/main/film/film.component';
import { BioskopComponent } from './components/main/bioskop/bioskop.component';
import { SalaComponent } from './components/main/sala/sala.component';
import { RezervacijaComponent } from './components/main/rezervacija/rezervacija.component';
import { AboutComponent } from './components/utility/about/about.component';
import { AuthorComponent } from './components/utility/author/author.component';
import { HomeComponent } from './components/utility/home/home.component';

const routes: Routes = [
  { path: 'film', component: FilmComponent },
  { path: 'bioskop', component: BioskopComponent },
  { path: 'sala', component: SalaComponent },
  { path: 'rezervacija', component: RezervacijaComponent },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'author', component: AuthorComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
