package br.com.gx.news.controller.form;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.gx.news.modelo.Usuario;
import br.com.gx.news.repository.JornalRepository;

public class CadastroForm {

	private String nome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario toUsuario(CadastroForm form, JornalRepository jornalRepository) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Usuario usuario = new Usuario();
		usuario.setEmail(form.getEmail());
		usuario.setNome(form.getNome());
		usuario.setSenha(encoder.encode(form.getSenha()));
		usuario.setJornais(jornalRepository.findFromStandardTable());
		
		return usuario;
	}

}
