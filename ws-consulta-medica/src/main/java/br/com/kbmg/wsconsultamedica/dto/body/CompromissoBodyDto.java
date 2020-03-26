package br.com.kbmg.wsconsultamedica.dto.body;

import java.time.LocalDateTime;

public class CompromissoBodyDto{

	private LocalDateTime dataHoraConsulta;
	private Long idMedico;
	private Long idPessoa;

	public LocalDateTime getDataHoraConsulta() {
		return dataHoraConsulta;
	}

	public void setDataHoraConsulta(LocalDateTime dataHoraConsulta) {
		this.dataHoraConsulta = dataHoraConsulta;
	}

	public Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	
}
