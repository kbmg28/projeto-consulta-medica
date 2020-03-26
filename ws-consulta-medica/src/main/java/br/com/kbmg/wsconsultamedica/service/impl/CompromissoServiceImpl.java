package br.com.kbmg.wsconsultamedica.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.wsconsultamedica.domain.Compromisso;
import br.com.kbmg.wsconsultamedica.domain.Medico;
import br.com.kbmg.wsconsultamedica.domain.Pessoa;
import br.com.kbmg.wsconsultamedica.dto.CompromissoDto;
import br.com.kbmg.wsconsultamedica.dto.CompromissoMedicoDto;
import br.com.kbmg.wsconsultamedica.dto.CompromissoPessoaDto;
import br.com.kbmg.wsconsultamedica.dto.body.CompromissoBodyDto;
import br.com.kbmg.wsconsultamedica.repository.CompromissoRepository;
import br.com.kbmg.wsconsultamedica.repository.MedicoRepository;
import br.com.kbmg.wsconsultamedica.repository.PessoaRepository;
import br.com.kbmg.wsconsultamedica.service.CompromissoService;
import br.com.kbmg.wsconsultamedica.utils.Util;

@Service
public class CompromissoServiceImpl extends GenericServiceImpl<Compromisso> implements CompromissoService {

	@Autowired
	CompromissoRepository repository;

	@Autowired
	MedicoRepository medicoRepository;

	@Autowired
	PessoaRepository pessoaRepository;

	@Override
	public CompromissoDto create(CompromissoBodyDto body) {
		Compromisso compromisso = new Compromisso();

		compromisso.setDataHoraConsulta(body.getDataHoraConsulta());

		Pessoa pessoa = this.getPessoa(body.getIdPessoa());

		Medico medico = this.getMedico(body.getIdMedico());

		compromisso.setMedico(medico);
		compromisso.setPessoa(pessoa);

		return (CompromissoDto) Util.convertObject(super.create(compromisso), CompromissoDto.class);
	}

	private Medico getMedico(Long idMedico) {

		return medicoRepository.findById(idMedico)
				.orElseThrow(() -> new EntityNotFoundException(msg.get("medico.nao.existe")));
	}

	private Pessoa getPessoa(Long idPessoa) {
		return pessoaRepository.findById(idPessoa)
				.orElseThrow(() -> new EntityNotFoundException(msg.get("pessoa.nao.existe")));
	}

	@Override
	public List<?> findAllByMedico(Long idMedico) {

		Medico medico = this.getMedico(idMedico);

		Optional<List<Compromisso>> opt = repository.findByMedico(medico);

		List<Compromisso> list = opt.orElseThrow(() -> new IllegalArgumentException(msg.get("medico.sem.compromisso")));

		return Util.convertList(list, CompromissoMedicoDto.class);
	}

	@Override
	public List<?> findAllByPessoa(Long idPessoa) {

		Pessoa pessoa = this.getPessoa(idPessoa);

		Optional<List<Compromisso>> opt = repository.findByPessoa(pessoa);

		List<Compromisso> list = opt.orElseThrow(() -> new IllegalArgumentException(msg.get("pessoa.sem.compromisso")));

		return Util.convertList(list, CompromissoPessoaDto.class);
	}

}
