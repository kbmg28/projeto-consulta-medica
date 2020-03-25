package br.com.kbmg.wsconsultamedica.dto.body;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioBodyDto {

	@Email(message="email inválido.")
	@NotBlank(message = "Email obrigatório")
	private String email;

	@NotBlank(message = "Senha obrigatório")
	private String senha;

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
