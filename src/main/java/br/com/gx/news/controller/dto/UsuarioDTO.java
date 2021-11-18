package br.com.gx.news.controller.dto;

import br.com.gx.news.modelo.Usuario;

public class UsuarioDTO {

	private String email;
	private String nome;

	public UsuarioDTO(Usuario usuario) {
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

}
