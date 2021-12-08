package br.com.gx.news.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gx.news.modelo.Jornal;

public interface JornalRepository extends JpaRepository<Jornal, Long>{

	Optional<Jornal> findByLink(String link);
	
	Optional<Jornal> findByNomeLikeIgnoreCase(String nome);
}
