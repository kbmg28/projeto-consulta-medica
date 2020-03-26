package br.com.kbmg.wsconsultamedica.dto.body;

import javax.validation.constraints.NotBlank;

public class MedicoBodyDto {

	@NotBlank(message = "Nome obrigatório")
	private String nomeMedico;

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

}
