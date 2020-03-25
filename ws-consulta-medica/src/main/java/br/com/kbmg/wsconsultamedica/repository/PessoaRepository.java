package br.com.kbmg.wsconsultamedica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kbmg.wsconsultamedica.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	Optional<Pessoa> findByEmail(String email);

}