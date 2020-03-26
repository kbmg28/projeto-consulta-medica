export * from './compromissoController.service';
import { CompromissoControllerService } from './compromissoController.service';
export * from './infoController.service';
import { InfoControllerService } from './infoController.service';
export * from './medicoController.service';
import { MedicoControllerService } from './medicoController.service';
export * from './pessoaController.service';
import { PessoaControllerService } from './pessoaController.service';
export const APIS = [CompromissoControllerService, InfoControllerService, MedicoControllerService, PessoaControllerService];
