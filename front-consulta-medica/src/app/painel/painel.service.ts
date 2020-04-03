import { Injectable, Output, Input } from '@angular/core';
import { BehaviorSubject, throwError } from 'rxjs';
import { Medico } from '../model/medico.model';
import { Compromisso } from '../model/compromisso.model';
import { CompromissoControllerService, MedicoControllerService } from '../typescript-angular-client';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PainelService {
  
  public dataMedico$: BehaviorSubject<Array<Medico>> = new BehaviorSubject<Array<Medico>>(new Array<Medico>());
  public dataCompromisso$: BehaviorSubject<Array<Compromisso>> = new BehaviorSubject<Array<Compromisso>>(new Array<Compromisso>());
  public errorAtual$: BehaviorSubject<string> = new BehaviorSubject<string>('');

  constructor(
    private compromissoApi: CompromissoControllerService, 
    private medicoApi: MedicoControllerService
  ) { }

  
  public getAllMedicos(){
    
    this.medicoApi.findAllUsingGET1().subscribe(resp => {
      this.dataMedico$.next(resp.data as Array<Medico>);
    },
    errorMessage => {
      this.handleError(errorMessage);
    })
  }
  
  public getConsultasByPessoa(){
    
    const userData: {
      nome: string;
      userId: string;
    } = JSON.parse(localStorage.getItem('userData'));
    
    this.compromissoApi.allByPessoaUsingGET(userData.userId).subscribe(resp => {

      this.dataCompromisso$.next(resp.data as Array<Compromisso>);
      this.errorAtual$.next('');
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
    this.errorAtual$.next(errorMessage);
    return throwError(errorMessage);
  }

}
