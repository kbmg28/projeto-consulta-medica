package br.com.kbmg.wsconsultamedica.dto;

public class PessoaDto {

	private String idPessoa;
	private String nomeCompleto;
	private String email;
	
	public String getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(String idPessoa) {
		this.idPessoa = idPessoa;
	}
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

}
