package br.com.gx.news.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "noticias")
public class Noticia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String link;
	private String jornal;
	private String titulo;

	
	public Noticia(String link, String jornal, String titulo) {
		this.link = link;
		this.jornal = jornal;
		this.titulo = titulo;
	}

	public String getLink() {
		return link;
	}

	public String getJornal() {
		return jornal;
	}

	public String getTitulo() {
		return titulo;
	}
	
	

}
