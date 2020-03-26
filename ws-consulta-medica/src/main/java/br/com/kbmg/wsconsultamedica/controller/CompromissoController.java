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

import br.com.kbmg.wsconsultamedica.dto.CompromissoDto;
import br.com.kbmg.wsconsultamedica.dto.body.CompromissoBodyDto;
import br.com.kbmg.wsconsultamedica.response.ObjectResponse;
import br.com.kbmg.wsconsultamedica.service.CompromissoService;
import br.com.kbmg.wsconsultamedica.utils.Util;
import br.com.kbmg.wsconsultamedica.utils.Validator;

@RestController
@RequestMapping(value = "/compromisso")
public class CompromissoController {

	@Autowired
	private CompromissoService service;

	@PostMapping("/create")
	public ResponseEntity<ObjectResponse> create(@Valid @RequestBody CompromissoBodyDto compromisso,
			BindingResult result) {
		return result.hasErrors() ? Util.responseBad(result) : Util.createResponseOk(service.create(compromisso));
	}

	@GetMapping
	public ResponseEntity<ObjectResponse> findOne(@Valid @RequestParam String idCompromisso) {
		return ResponseEntity.ok(new ObjectResponse(service.findById(idCompromisso)));
	}

	@GetMapping("/all")
	public ResponseEntity<ObjectResponse> findAll() {
		return ResponseEntity.ok(new ObjectResponse(service.findAllDto(CompromissoDto.class)));
	}

	@GetMapping("/allByMedico")
	public ResponseEntity<ObjectResponse> allByMedico(@Valid @RequestParam String idMedico) {
		return ResponseEntity.ok(
				new ObjectResponse(service.findAllByMedico(Validator.stringParseLong(idMedico, "idMedico inválido"))));
	}

	@GetMapping("/allByPessoa")
	public ResponseEntity<ObjectResponse> allByPessoa(@Valid @RequestParam String idPessoa) {
		return ResponseEntity.ok(
				new ObjectResponse(service.findAllByPessoa(Validator.stringParseLong(idPessoa, "idPessoa inválido"))));
	}

	@DeleteMapping("/deleteOne")
	public ResponseEntity<ObjectResponse> deleteById(@Valid @RequestParam String idCompromisso) {
		this.service.deleteById(idCompromisso);
		return ResponseEntity.ok(new ObjectResponse("Objeto deletado com sucesso"));
	}

}