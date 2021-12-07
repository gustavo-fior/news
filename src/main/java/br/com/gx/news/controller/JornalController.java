package br.com.gx.news.controller;

import java.net.URI;
import java.util.List;

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

import br.com.gx.news.controller.dto.JornalDTO;
import br.com.gx.news.controller.form.JornalForm;
import br.com.gx.news.modelo.Jornal;
import br.com.gx.news.repository.JornalRepository;
import br.com.gx.news.validacao.ValidacaoJornalService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/newspaper")
public class JornalController {

	@Autowired
	private ValidacaoJornalService jornalService;

	@Autowired
	private JornalRepository jornalRepository;

	@GetMapping
	public ResponseEntity<List<Jornal>> getJornais(HttpServletRequest req) {

		List<Jornal> jornais = jornalRepository.findAll();

		return ResponseEntity.ok(jornais);

	}

	@ApiIgnore
	@PostMapping
	public ResponseEntity<JornalDTO> adicionarNovoJornal(@RequestBody @Valid JornalForm form,
			UriComponentsBuilder uriBuilder) {

		List<Jornal> jornais = jornalRepository.findAll();

		Jornal jornal = form.toJornal();

		if (jornalService.isJornalInJornais(jornal, jornais, jornalRepository)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		jornalRepository.save(jornal);

		URI uri = uriBuilder.path("/jornal/{id}").buildAndExpand(jornal.getId()).toUri();

		return ResponseEntity.created(uri).body(new JornalDTO(jornal));

	}

}
