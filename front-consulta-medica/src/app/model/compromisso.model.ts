import { Medico } from './medico.model';

export class Compromisso {
  constructor(
    public idCompromisso: number,
    public dataHoraConsulta: string,
    public medico: Medico,
  ) {}
}
