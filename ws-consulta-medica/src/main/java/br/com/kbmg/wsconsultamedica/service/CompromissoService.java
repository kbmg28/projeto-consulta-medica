package br.com.kbmg.wsconsultamedica.service;

import java.util.List;

import br.com.kbmg.wsconsultamedica.domain.Compromisso;
import br.com.kbmg.wsconsultamedica.dto.CompromissoDto;
import br.com.kbmg.wsconsultamedica.dto.body.CompromissoBodyDto;

public interface CompromissoService extends GenericService<Compromisso> {

	CompromissoDto create(CompromissoBodyDto body);

	List<?> findAllByMedico(Long idMedico);

	List<?> findAllByPessoa(Long idPessoa);

}
