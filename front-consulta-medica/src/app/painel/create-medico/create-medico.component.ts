import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { MedicoControllerService, MedicoBodyDto } from 'src/app/typescript-angular-client';
import { Medico } from 'src/app/model/medico.model';
import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-create-medico',
  templateUrl: './create-medico.component.html',
  styleUrls: ['./create-medico.component.css']
})
export class CreateMedicoComponent {

  @Input()id: number;
  myForm: FormGroup;
  medico: MedicoBodyDto;
  error: string = null;

  constructor(
   public activeModal: NgbActiveModal,
   private formBuilder: FormBuilder,
   private medicoApi: MedicoControllerService
  ) {
    this.createForm();
  }
  

  private createForm() {
    this.myForm = this.formBuilder.group({
      nomeMedico: ''
    });
  }
  
  public submitForm() {
    let medicoBodyDto: MedicoBodyDto = {
      nomeMedico: this.myForm.value.nomeMedico
    };
    this.medicoApi.createUsingPOST1(medicoBodyDto).subscribe(resp =>{

    },
    errorMessage => {
      this.handleError(errorMessage);
    })
    
    this.activeModal.close(this.myForm.value);
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
