package br.com.kbmg.wsconsultamedica.service.impl;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.kbmg.wsconsultamedica.domain.Pessoa;
import br.com.kbmg.wsconsultamedica.dto.PessoaDto;
import br.com.kbmg.wsconsultamedica.dto.body.PessoaBodyDto;
import br.com.kbmg.wsconsultamedica.dto.body.UsuarioBodyDto;
import br.com.kbmg.wsconsultamedica.repository.PessoaRepository;
import br.com.kbmg.wsconsultamedica.service.PessoaService;
import br.com.kbmg.wsconsultamedica.utils.Util;

@Service
public class PessoaServiceImpl extends GenericServiceImpl<Pessoa> implements PessoaService {

	@Autowired
	PessoaRepository repository;

	@Override
	public PessoaDto create(PessoaBodyDto body) {
		Pessoa pessoa = this.criaPessoa(body);

		if (repository.findByEmail(pessoa.getEmail()).isPresent()) {
			throw new EntityExistsException(msg.get("email.registrado"));
		}

		return (PessoaDto) Util.convertObject(this.saveEntity(pessoa), PessoaDto.class);
	}

	private Pessoa criaPessoa(PessoaBodyDto body) {
		Pessoa pessoa = (Pessoa) Util.convertObject(body, Pessoa.class);

		pessoa.setSenha(BCrypt.hashpw(body.getSenha(), BCrypt.gensalt()));

		return pessoa;
	}

	@Override
	public PessoaDto auth(UsuarioBodyDto usuario) {
		Optional<Pessoa> opt = repository.findByEmail(usuario.getEmail());

		opt.orElseThrow(() -> new EntityNotFoundException(msg.get("usuario.nao.existe")));

		Pessoa present = opt.filter(p -> BCrypt.checkpw(usuario.getSenha(), p.getSenha()))
				.orElseThrow(() -> new IllegalArgumentException(msg.get("usuario.senha.invalida")));

		return (PessoaDto) Util.convertObject(present, PessoaDto.class);
	}

}
