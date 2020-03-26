package br.com.kbmg.wsconsultamedica.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.wsconsultamedica.domain.Compromisso;
import br.com.kbmg.wsconsultamedica.domain.Medico;
import br.com.kbmg.wsconsultamedica.dto.MedicoDto;
import br.com.kbmg.wsconsultamedica.dto.body.MedicoBodyDto;
import br.com.kbmg.wsconsultamedica.repository.MedicoRepository;
import br.com.kbmg.wsconsultamedica.service.MedicoService;
import br.com.kbmg.wsconsultamedica.utils.Util;

@Service
public class MedicoServiceImpl extends GenericServiceImpl<Medico> implements MedicoService {

	@Autowired
	MedicoRepository repository;

	@Override
	public MedicoDto create(MedicoBodyDto body) {
		Medico medico = super.create( (Medico) Util.convertObject(body, Medico.class));
		return (MedicoDto) Util.convertObject(medico, MedicoDto.class);
	}
	
	@Override
	public void deleteById(String id) {
		Medico medico = this.findById(id);
		
		Optional<List<Compromisso>> opt = Optional.of(medico.getCompromissos());
		
		if (opt.isPresent()) {
			throw new IllegalArgumentException(msg.get("medico.com.compromisso"));
		}
		super.deleteById(id);
	}

}
