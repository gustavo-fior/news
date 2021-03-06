package br.com.gx.news.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gx.news.controller.dto.TrendComNoticiasDTO;
import br.com.gx.news.controller.dto.TrendDTO;
import br.com.gx.news.controller.form.TrendForm;
import br.com.gx.news.modelo.Jornal;
import br.com.gx.news.modelo.Noticia;
import br.com.gx.news.modelo.Trend;
import br.com.gx.news.repository.JornalRepository;
import br.com.gx.news.repository.TrendRepository;
import br.com.gx.news.service.NewsService;

@RestController
@RequestMapping("/trends")
public class TrendController {

	@Autowired
	private TrendRepository trendRepository;

	@Autowired
	private JornalRepository jornalRepository;

	@Autowired
	private NewsService newsService;

	@GetMapping
	public ResponseEntity<Set<TrendDTO>> getTrends() {

		List<Trend> trends = trendRepository.findAll();

		Set<TrendDTO> trendsDTO = new HashSet<>();

		trends.forEach(trend -> {
			trendsDTO.add(new TrendDTO(trend));
		});

		return ResponseEntity.ok(trendsDTO);

	}

	@GetMapping("/{trendLink}")
	public ResponseEntity<TrendComNoticiasDTO> getTrendsComNoticias(@PathVariable String trendLink) {

		Trend trend = trendRepository.findByLink(trendLink).get();

		List<Jornal> jornais = jornalRepository.findAll();
		Set<Noticia> noticias = new HashSet<>();

		jornais.forEach(jornal -> {
			newsService.getSetComNoticiasBaseadoNoJornal(trendLink, jornal, noticias);
		});

		return ResponseEntity.ok(new TrendComNoticiasDTO(trend, noticias));

	}

	@PostMapping("/add")
	public ResponseEntity<TrendDTO> addTrend(@RequestBody @Valid TrendForm form, UriComponentsBuilder uriBuilder) {

		Trend trend = form.convert();

		Optional<Trend> optionalTrend = trendRepository.findByLink(trend.getLink());

		if (optionalTrend.isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		trendRepository.save(trend);

		URI uri = uriBuilder.path("/trend/{id}").buildAndExpand(trend.getId()).toUri();

		return ResponseEntity.created(uri).body(new TrendDTO(trend));
	}

}
