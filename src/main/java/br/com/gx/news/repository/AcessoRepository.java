package br.com.gx.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gx.news.modelo.Acesso;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long>{

}
