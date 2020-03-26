package br.com.kbmg.wsconsultamedica.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;

@Entity
public class Medico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMedico;

	@NotBlank(message = "Nome da médico obrigatório")
	private String nomeMedico;

	@OneToMany(mappedBy = "medico", targetEntity = Compromisso.class)
	private List<Compromisso> compromissos = new ArrayList<Compromisso>();

	
	public Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public List<Compromisso> getCompromissos() {
		return compromissos;
	}

	public void setCompromissos(List<Compromisso> compromissos) {
		this.compromissos = compromissos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMedico == null) ? 0 : idMedico.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		if (idMedico == null) {
			if (other.idMedico != null)
				return false;
		} else if (!idMedico.equals(other.idMedico))
			return false;
		return true;
	}

	@PrePersist
	public void PrePersist() {
		this.nomeMedico = this.nomeMedico.toUpperCase();
	}

}
