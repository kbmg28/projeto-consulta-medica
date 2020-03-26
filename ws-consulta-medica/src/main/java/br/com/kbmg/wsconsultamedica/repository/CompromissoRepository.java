package br.com.kbmg.wsconsultamedica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kbmg.wsconsultamedica.domain.Compromisso;
import br.com.kbmg.wsconsultamedica.domain.Medico;
import br.com.kbmg.wsconsultamedica.domain.Pessoa;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long>{

	Optional<List<Compromisso>> findByMedico(Medico medico);

	Optional<List<Compromisso>> findByPessoa(Pessoa pessoa);

}