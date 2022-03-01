package br.com.gx.news.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gx.news.modelo.Trend;

@Repository
public interface TrendRepository extends JpaRepository<Trend, Long>{

	Optional<Trend> findByLink(String link);
	
}
