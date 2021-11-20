package br.com.gx.news.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gx.news.controller.dto.UsuarioDTO;
import br.com.gx.news.controller.form.CadastroForm;
import br.com.gx.news.modelo.Perfil;
import br.com.gx.news.modelo.Usuario;
import br.com.gx.news.repository.JornalRepository;
import br.com.gx.news.repository.PerfilRepository;
import br.com.gx.news.repository.UsuarioRepository;
import br.com.gx.news.validacao.ValidacaoUsuarioService;

@RestController
@RequestMapping("/signup")
public class SignUpController {

	@Autowired
	private ValidacaoUsuarioService validacao;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private JornalRepository jornalRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	@PostMapping
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid CadastroForm form,
			UriComponentsBuilder uriBuilder) {

		Perfil userPerfil = perfilRepository.findByNome("ROLE_USER");

		Usuario usuario = form.toUsuario(form, jornalRepository);
		usuario.adicionarPerfil(userPerfil);

		if (validacao.isUsuarioUnico(usuario)) {

			usuarioRepository.save(usuario);

			URI uri = uriBuilder.path("user/{id}").buildAndExpand(usuario.getId()).toUri();

			return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));

		}

		return ResponseEntity.badRequest().build();

	}

}
