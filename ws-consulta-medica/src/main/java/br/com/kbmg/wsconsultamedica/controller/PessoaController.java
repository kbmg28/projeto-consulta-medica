package br.com.kbmg.wsconsultamedica.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.wsconsultamedica.dto.body.PessoaBodyDto;
import br.com.kbmg.wsconsultamedica.dto.body.UsuarioBodyDto;
import br.com.kbmg.wsconsultamedica.response.ObjectResponse;
import br.com.kbmg.wsconsultamedica.service.PessoaService;
import br.com.kbmg.wsconsultamedica.utils.Util;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@PostMapping("/signup")
	public ResponseEntity<ObjectResponse> create(@Valid @RequestBody PessoaBodyDto pessoa, BindingResult result) {
		return result.hasErrors() ? Util.responseBad(result)
				: Util.createResponseOk(service.create(pessoa));
	}

	@PostMapping("/login")
	public ResponseEntity<ObjectResponse> auth(@Valid @RequestBody UsuarioBodyDto usuario, BindingResult result) {
		return result.hasErrors() ? Util.responseBad(result)
				: Util.createResponseOk(service.auth(usuario));
	}

	@GetMapping
	public ResponseEntity<ObjectResponse> findOne(@Valid @RequestParam String idPessoa) {
		return ResponseEntity.ok(new ObjectResponse(service.findById(idPessoa)));
	}

	@GetMapping("/all")
	public ResponseEntity<ObjectResponse> findAll() {
		return ResponseEntity.ok(new ObjectResponse(service.findAll()));
	}

	@DeleteMapping("/deleteOne")
	public ResponseEntity<ObjectResponse> deleteById(@Valid @RequestParam String idPessoa) {
		this.service.deleteById(idPessoa);
		return ResponseEntity.ok(new ObjectResponse("Objeto deletado com sucesso"));
	}

}