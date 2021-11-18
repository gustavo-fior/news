package br.com.gx.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gx.news.modelo.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long>{

}
