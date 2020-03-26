import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  
  constructor(private authService: AuthService) {}

  title = 'front-consulta-medica';

  ngOnInit() {
    this.authService.autoLogin();
  }
}
