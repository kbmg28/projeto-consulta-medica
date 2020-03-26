import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs/operators';
import { throwError, BehaviorSubject, fromEventPattern } from 'rxjs';
import { PessoaControllerService } from '../typescript-angular-client/api/pessoaController.service'

import { User } from './user.model';

export interface PessoaBodyDto { 
  email?: string;
  nomeCompleto?: string;
  senha?: string;
}

export interface UsuarioBodyDto { 
  email?: string;
  senha?: string;
}

export interface AuthResponseData {
  idPessoa: string;
  nomeCompleto: string;
  email: string;
  registered?: boolean;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  user = new BehaviorSubject<User>(null);

  constructor(private http: HttpClient, private router: Router, private pessoaApi: PessoaControllerService) {}

  signup(pessoa: PessoaBodyDto) {
    return this.pessoaApi.createUsingPOST2(pessoa)
      .pipe(
        catchError(this.handleError),
        tap(resData => {
          const { email, nomeCompleto, idPessoa} = resData.data;
          this.handleAuthentication(idPessoa, nomeCompleto, email);
        })
      );
  }

  login(usuario: UsuarioBodyDto) {
    return this.pessoaApi.authUsingPOST(usuario)
      .pipe(
        catchError(this.handleError),
        tap(resData => {
          const { email, nomeCompleto, idPessoa} = resData.data;
          this.handleAuthentication(idPessoa, nomeCompleto, email);
        })
      );
  }

  autoLogin() {
    const userData: {
      email: string;
      nome: string;
      userId: string;
    } = JSON.parse(localStorage.getItem('userData'));
    if (!userData) {
      return;
    }

    const loadedUser = new User(
      userData.email,
      userData.userId,
      userData.nome
    );

    if (loadedUser) {
      this.user.next(loadedUser);
    }
  }

  logout() {
    this.user.next(null);
    this.router.navigate(['/auth']);
    localStorage.removeItem('userData');
  }

  private handleAuthentication(
    nome: string,
    email: string,
    userId: string,
  ) {
    const user = new User(email, userId, nome);
    this.user.next(user);
    localStorage.setItem('userData', JSON.stringify(user));
  }

  private handleError(errorRes: HttpErrorResponse) {

    let errorMessage = 'An unknown error occurred!';
    if (!errorRes.error || !errorRes.error.errorDescription) {
      return throwError(errorMessage);
    }
    
    errorMessage = errorRes.error.errorDescription.message

    return throwError(errorMessage);
  }
}
