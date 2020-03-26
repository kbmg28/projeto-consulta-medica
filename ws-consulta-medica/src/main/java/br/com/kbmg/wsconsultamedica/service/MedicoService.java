package br.com.kbmg.wsconsultamedica.service;

import br.com.kbmg.wsconsultamedica.domain.Medico;
import br.com.kbmg.wsconsultamedica.dto.MedicoDto;
import br.com.kbmg.wsconsultamedica.dto.body.MedicoBodyDto;

public interface MedicoService extends GenericService<Medico> {

	MedicoDto create(MedicoBodyDto body);

}
