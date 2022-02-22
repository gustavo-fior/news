package br.com.gx.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gx.news.modelo.Trend;

@Repository
public interface TrendRepository extends JpaRepository<Trend, Long>{

	Trend findByLink(String link);
	
}
