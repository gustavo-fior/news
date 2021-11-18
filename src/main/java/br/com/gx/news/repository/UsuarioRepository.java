package br.com.gx.news.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gx.news.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}
