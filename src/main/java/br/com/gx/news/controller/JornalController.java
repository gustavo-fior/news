package br.com.gx.news.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gx.news.config.security.AutenticacaoViaTokenFilter;
import br.com.gx.news.config.security.TokenService;
import br.com.gx.news.controller.dto.JornalDTO;
import br.com.gx.news.controller.form.JornalForm;
import br.com.gx.news.modelo.Jornal;
import br.com.gx.news.modelo.Usuario;
import br.com.gx.news.repository.JornalRepository;
import br.com.gx.news.repository.UsuarioRepository;
import br.com.gx.news.validacao.ValidacaoJornalService;

@RestController
@RequestMapping("/jornal")
public class JornalController {

	@Autowired
	private TokenService service;

	@Autowired
	private ValidacaoJornalService jornalService;
	
	@Autowired
	private JornalRepository jornalRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ResponseEntity<Set<Jornal>> getJornais(HttpServletRequest req) {

		AutenticacaoViaTokenFilter tokenFilter = criarFilter(service, usuarioRepository);

		String token = tokenFilter.recuperarToken(req);
		Long id = service.getIdUsuario(token);

		Set<Jornal> jornais = jornalRepository.findByUsuario(id);

		return ResponseEntity.ok(jornais);

	}

	// Repensar essa lógica 
	@PostMapping
	public ResponseEntity<JornalDTO> adicionarNovoJornal(@RequestBody @Valid JornalForm form, HttpServletRequest req,
			UriComponentsBuilder uriBuilder) {

		AutenticacaoViaTokenFilter tokenFilter = criarFilter(service, usuarioRepository);

		String token = tokenFilter.recuperarToken(req);
		Long id = service.getIdUsuario(token);

		Set<Jornal> jornais = jornalRepository.findByUsuario(id);
		
		Jornal jornal = form.toJornal();

		if (jornalService.isJornalInJornais(jornal, jornais, jornalRepository)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		jornais.add(jornal);
		
		//Arrumar lógica para jornal ser salvo na lista do usuario
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		usuario.get().setJornais(new ArrayList<Jornal>(jornais));

		URI uri = uriBuilder.path("/jornal/{id}").buildAndExpand(jornal.getId()).toUri();

		return ResponseEntity.created(uri).body(new JornalDTO(jornal));

	}

	private AutenticacaoViaTokenFilter criarFilter(TokenService service, UsuarioRepository repo) {
		return new AutenticacaoViaTokenFilter(service, repo);
	}

}
