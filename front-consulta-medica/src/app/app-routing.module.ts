import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { PainelComponent } from './painel/painel.component';
import { StartComponent } from './painel/start/start.component';
import { EditComponent } from './painel/edit/edit.component';
import { DetailComponent } from './painel/detail/detail.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: '/painel', pathMatch: 'full' },
  { path: 'auth', component: AuthComponent },
  {
    path: 'painel',
    component: PainelComponent,
    canActivate: [AuthGuard],
    children: [
      {path: '', component: StartComponent},
      {path: 'new', component: EditComponent},
      {path: ':id', component: DetailComponent},
      {path: ':id/edit', component: EditComponent},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
