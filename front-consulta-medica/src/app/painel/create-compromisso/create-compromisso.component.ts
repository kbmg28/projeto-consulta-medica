import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MedicoControllerService, CompromissoBodyDto, CompromissoControllerService } from 'src/app/typescript-angular-client';
import { Medico } from 'src/app/model/medico.model';
import { PainelService } from '../painel.service';

@Component({
  selector: 'app-create-compromisso',
  templateUrl: './create-compromisso.component.html',
  styleUrls: ['./create-compromisso.component.css']
})
export class CreateCompromissoComponent {

  @Input()id: number;
  myForm: FormGroup;
  error: string = null;
  
  selectedMedico: Medico = new Medico(null, null);
  listMedicos: Array<Medico> = new Array();
  formControlName
  constructor(
   public activeModal: NgbActiveModal,
   private formBuilder: FormBuilder,
   private compromissoApi: CompromissoControllerService
   , private painelService: PainelService
  ) {
    this.createForm();
    this.getMedicos();
  }
  

  private createForm() {
    this.myForm = this.formBuilder.group({
      medico: ['', Validators.required]
    });
  }
  
  private getMedicos(){
    this.painelService.getAllMedicos();
   this.painelService.dataMedico$.subscribe( resp => {
     this.listMedicos = resp;
   })
  }
  
  public submitForm() {
    
    const userData: {
      nome: string;
      userId: number;
    } = JSON.parse(localStorage.getItem('userData'));

    let compromissoBodyDto: CompromissoBodyDto = {
      idMedico: this.selectedMedico.idMedico,
      idPessoa: userData.userId
    };
    
    this.compromissoApi.createUsingPOST(compromissoBodyDto).subscribe(resp =>{
          
    })
    
    this.activeModal.close(this.myForm.value);
  }
   

}
