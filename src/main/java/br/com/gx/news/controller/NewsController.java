package br.com.gx.news.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gx.news.modelo.Jornal;
import br.com.gx.news.modelo.Noticia;
import br.com.gx.news.repository.JornalRepository;
import br.com.gx.news.service.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private JornalRepository jornalRepository;
	
	@Autowired
	private NewsService newsService;

	@GetMapping("/{palavra}")
	public ResponseEntity<Set<Noticia>> getNoticias(@PathVariable String palavra) {

		Set<Noticia> noticias = new HashSet<Noticia>();
		List<Jornal> jornais = jornalRepository.findAll();

		jornais.forEach(jornal -> newsService.getSetComNoticiasBaseadoNoJornal(palavra, jornal, noticias));

		if (noticias.size() == 0) {
			noticias.add(new Noticia("mailto:gustavo_fior@outlook.com", "Não achamos nenhuma notícia :(",
					"Se você acha que isso é um erro, clique no link abaixo e nos avise!"));
		}

		return ResponseEntity.ok(noticias);

	}

	@GetMapping("/{nome}/{palavra}")
	public ResponseEntity<Set<Noticia>> getNoticiasPorJornais(@PathVariable String nome, @PathVariable String palavra) {

		Optional<Jornal> jornal = jornalRepository.findByNomeLikeIgnoreCase("%" + nome + "%");

		if (!jornal.isPresent())
			return ResponseEntity.badRequest().build();

		Set<Noticia> noticias = new HashSet<Noticia>();

		newsService.getSetComNoticiasBaseadoNoJornal(palavra, jornal.get(), noticias);
		
		if (noticias.size() == 0) {
			noticias.add(new Noticia("mailto:gustavo_fior@outlook.com", "Não achamos nenhuma notícia :(",
					"Se você acha que isso é um erro, clique no link abaixo e nos avise!"));
		}

		return ResponseEntity.ok(noticias);

	}

	

}
