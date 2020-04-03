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
import {NgbModal, ModalDismissReasons, NgbModalOptions} from '@ng-bootstrap/ng-bootstrap';
import { CreateMedicoComponent } from '../create-medico/create-medico.component';
import { FormGroup, FormBuilder } from '@angular/forms';
import { CreateCompromissoComponent } from '../create-compromisso/create-compromisso.component';
import {PainelService} from '../painel.service';
 
@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  
 editProfileForm: FormGroup;
 
  headElementsMedico = ['#', 'Médico'];
  listMedicos: Array<Medico>;

  headElements = ['#', 'Médico', 'Data/Hora'];
  listConsultas: Array<Compromisso> = new Array();
  error: string = null;
  nome: string = null;

  title = 'ng-bootstrap-modal-demo';
  closeResult: string;
  modalOptions:NgbModalOptions;
 
  constructor(private fb: FormBuilder, private modalService: NgbModal, private painelService: PainelService, private compromissoApi: CompromissoControllerService, private medicoApi: MedicoControllerService) { }
  
  ngOnInit(): void {
    const userData: { 
      nome: string;
    } = JSON.parse(localStorage.getItem('userData'));
    this.nome = 'Olá, ' + userData.nome + ' !!';
    this.getMedicos();
    this.getConsultasByPessoa();

    this.modalOptions = {
      backdrop:'static',
      backdropClass:'customBackdrop'
    }
  }
  
  private getMedicos(){
   this.painelService.getAllMedicos();
   this.painelService.dataMedico$.subscribe( resp => {
     this.listMedicos = resp;
   })
  }
  
  private getConsultasByPessoa(){
    this.painelService.getConsultasByPessoa();
   this.painelService.dataCompromisso$.subscribe( resp => {
     this.listConsultas = resp;
   })
   this.verificaErro();
  }
  
  private verificaErro(){
    this.painelService.getConsultasByPessoa();
   this.painelService.errorAtual$.subscribe( resp => {
     this.error = resp;
   })
  }
  
  
  public open(cadastraMedico:boolean) {
    const modalRef = this.modalService.open(cadastraMedico ? CreateMedicoComponent : CreateCompromissoComponent, {
        windowClass: 'modal-holder',
        centered: true
    });
  }
}
