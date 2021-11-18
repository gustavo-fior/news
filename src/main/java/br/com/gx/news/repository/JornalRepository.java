package br.com.gx.news.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gx.news.modelo.Jornal;

public interface JornalRepository extends JpaRepository<Jornal, Long>{

	@Query(value = "SELECT * FROM jornais_standard", nativeQuery = true)
	List<Jornal> findFromStandardTable();
	
	@Query(value = "SELECT * FROM jornais A INNER JOIN usuarios_jornais B ON A.id = B.jornais_id WHERE B.usuario_id = :id", nativeQuery = true)
	Set<Jornal> findByUsuario(Long id);

	Optional<Jornal> findByLink(String link);
}
