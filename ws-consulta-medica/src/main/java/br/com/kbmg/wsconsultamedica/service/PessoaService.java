package br.com.kbmg.wsconsultamedica.service;

import br.com.kbmg.wsconsultamedica.domain.Pessoa;
import br.com.kbmg.wsconsultamedica.dto.PessoaDto;
import br.com.kbmg.wsconsultamedica.dto.body.PessoaBodyDto;
import br.com.kbmg.wsconsultamedica.dto.body.UsuarioBodyDto;

public interface PessoaService extends GenericService<Pessoa> {

	PessoaDto create(PessoaBodyDto body);

	PessoaDto auth(UsuarioBodyDto usuario);

}
