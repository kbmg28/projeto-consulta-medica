import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { Configuration } from './configuration';
import { HttpClient } from '@angular/common/http';


import { CompromissoControllerService } from './api/compromissoController.service';
import { InfoControllerService } from './api/infoController.service';
import { MedicoControllerService } from './api/medicoController.service';
import { PessoaControllerService } from './api/pessoaController.service';

@NgModule({
  imports:      [],
  declarations: [],
  exports:      [],
  providers: [
    CompromissoControllerService,
    InfoControllerService,
    MedicoControllerService,
    PessoaControllerService ]
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): ModuleWithProviders {
        return {
            ngModule: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        };
    }

    constructor( @Optional() @SkipSelf() parentModule: ApiModule,
                 @Optional() http: HttpClient) {
        if (parentModule) {
            throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
        }
        if (!http) {
            throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
            'See also https://github.com/angular/angular/issues/20575');
        }
    }
}
