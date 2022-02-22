package br.com.gx.news.dto;

import java.util.Set;

import br.com.gx.news.modelo.Noticia;
import br.com.gx.news.modelo.Trend;

public class TrendComNoticiasDTO {

	private String titulo;
	private String image;
	private String descricao;
	private String link;
	private Set<Noticia> noticias;

	public TrendComNoticiasDTO(Trend trend, Set<Noticia> noticias) {
		this.titulo = trend.getTitulo();
		this.image = trend.getImage();
		this.link = trend.getLink();
		this.descricao = trend.getDescricao();
		this.noticias = noticias;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getImage() {
		return image;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getLink() {
		return link;
	}

	public Set<Noticia> getNoticias() {
		return noticias;
	}

}
