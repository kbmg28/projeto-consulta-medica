package br.com.kbmg.wsconsultamedica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
