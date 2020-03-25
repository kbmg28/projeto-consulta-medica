package br.com.kbmg.wsconsultamedica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kbmg.wsconsultamedica.domain.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

}