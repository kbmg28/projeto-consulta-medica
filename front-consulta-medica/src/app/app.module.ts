import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ApiModule } from './typescript-angular-client';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { AuthComponent } from './auth/auth.component';
import { PainelComponent } from './painel/painel.component';
import { EditComponent } from './painel/edit/edit.component';
import { ListComponent } from './painel/list/list.component';
import { DetailComponent } from './painel/detail/detail.component';
import { StartComponent } from './painel/start/start.component';
import { CreateMedicoComponent } from './painel/create-medico/create-medico.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CreateCompromissoComponent } from './painel/create-compromisso/create-compromisso.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AuthComponent,
    PainelComponent,
    EditComponent,
    ListComponent,
    DetailComponent,
    StartComponent,
    CreateMedicoComponent,
    CreateCompromissoComponent,
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ApiModule,
    NgbModule
  ],
  entryComponents:[
    CreateMedicoComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
