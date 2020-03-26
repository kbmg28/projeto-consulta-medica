import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { AuthService, AuthResponseData, UsuarioBodyDto } from './auth.service';
import { PessoaBodyDto, ObjectResponse } from '../typescript-angular-client';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html'
})
export class AuthComponent {
  isLoginMode = true;
  isLoading = false;
  error: string = null;

  constructor(private authService: AuthService, private router: Router) {}

  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
  }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }
    const email = form.value.email;
    const senha = form.value.senha;
    const nome = form.value.nome;

    
    let authObs: Observable<ObjectResponse>;

    let pessoaDTO: PessoaBodyDto = {
      email: email,
      nomeCompleto: nome,
      senha: senha,
    };

    let usuarioDTO: UsuarioBodyDto = {
      email: email,
      senha: senha,
    };

    this.isLoading = true;

    if (this.isLoginMode) {
      authObs = this.authService.login(usuarioDTO);
    } else {
      authObs = this.authService.signup(pessoaDTO);
    }

    authObs.subscribe(
      resData => {
        console.log(resData);
        this.isLoading = false;
        this.router.navigate(['/painel']);
      },
      errorMessage => {
        console.log(errorMessage);
        this.error = errorMessage;
        this.isLoading = false;
      }
    );

    form.reset();
  }
}
