import { Component, OnInit } from '@angular/core';
import { PessoaControllerService } from 'src/app/typescript-angular-client/api/pessoaController.service';
import { CompromissoControllerService } from 'src/app/typescript-angular-client';
import { MedicoControllerService } from 'src/app/typescript-angular-client';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { throwError, BehaviorSubject, fromEventPattern } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Compromisso } from 'src/app/model/compromisso.model';
import { Medico } from 'src/app/model/medico.model';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  
  headElementsMedico = ['#', 'Médico'];
  listMedicos: Array<Medico> = new Array();

  headElements = ['#', 'Médico', 'Data/Hora'];
  list: Array<Compromisso> = new Array();
  error: string = null;
  nome: string = null;
  
  constructor(private compromissoApi: CompromissoControllerService, private medicoApi: MedicoControllerService) { }
  
  ngOnInit(): void {
    const userData: {
      nome: string;
    } = JSON.parse(localStorage.getItem('userData'));
    this.nome = 'Bem vindo ' + userData.nome + ' !!';
    this.getMedicos();
    this.getConsultasByPessoa();
    
  }
  
  private getMedicos(){
    
    this.medicoApi.findAllUsingGET1().subscribe(resp => {
      resp.data.forEach(e => {
        this.listMedicos.push(e);
      });
      this.error = null;
    },
    errorMessage => {
      this.handleError(errorMessage);
    })
  }
  
  private getConsultasByPessoa(){
    
    const userData: {
      nome: string;
      userId: string;
    } = JSON.parse(localStorage.getItem('userData'));
    
    this.compromissoApi.allByPessoaUsingGET(userData.userId).subscribe(resp => {
      resp.data.forEach(element => {
        this.list.push(element);
      });
      this.error = null;
    },
    errorMessage => {
      this.handleError(errorMessage);
    })
  }
  
  private handleError(errorRes: HttpErrorResponse) {
    
    let errorMessage = 'Erro desconhecido!';
    if (!errorRes.error || !errorRes.error.errorDescription) {
      return throwError(errorMessage);
    }
    
    errorMessage = errorRes.error.errorDescription.message
    this.error = errorMessage;
    return throwError(errorMessage);
  }
  
}
