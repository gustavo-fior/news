package br.com.gx.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gx.news.modelo.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{

	Perfil findByNome(String nome);
	
}
