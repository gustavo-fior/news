package br.com.gx.news.controller.dto;

import br.com.gx.news.modelo.Trend;

public class TrendDTO {

	private String titulo;
	private String image;
	private String descricao;
	private String link;

	public TrendDTO(Trend trend) {
		this.titulo = trend.getTitulo();
		this.image = trend.getImage();
		this.link = trend.getLink();
		this.descricao = trend.getDescricao();
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

}
