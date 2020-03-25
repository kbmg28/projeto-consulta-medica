package br.com.kbmg.wsconsultamedica.dto.body;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PessoaBodyDto {

	@NotBlank(message = "Nome obrigatório")
	private String nomeCompleto;
	
	@Email(message="email inválido.")
	@NotBlank(message = "Email obrigatório")
	private String email;

	@NotBlank(message = "Senha obrigatório")
	private String senha;

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
