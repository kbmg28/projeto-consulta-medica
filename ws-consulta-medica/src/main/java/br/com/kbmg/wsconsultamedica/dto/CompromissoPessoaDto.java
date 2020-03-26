package br.com.kbmg.wsconsultamedica.dto;

import java.time.LocalDateTime;

public class CompromissoPessoaDto{

	private Long idCompromisso;
	private LocalDateTime dataHoraConsulta;
	private MedicoDto medico;

	public Long getIdCompromisso() {
		return idCompromisso;
	}

	public void setIdCompromisso(Long idCompromisso) {
		this.idCompromisso = idCompromisso;
	}

	public LocalDateTime getDataHoraConsulta() {
		return dataHoraConsulta;
	}

	public void setDataHoraConsulta(LocalDateTime dataHoraConsulta) {
		this.dataHoraConsulta = dataHoraConsulta;
	}

	public MedicoDto getMedico() {
		return medico;
	}

	public void setMedico(MedicoDto medico) {
		this.medico = medico;
	}
	
}
