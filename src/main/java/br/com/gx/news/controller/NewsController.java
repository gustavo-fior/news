package br.com.gx.news.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gx.news.modelo.Jornal;
import br.com.gx.news.modelo.Noticia;
import br.com.gx.news.repository.JornalRepository;

@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private JornalRepository jornalRepository;

	@GetMapping("/{palavra}")
	public ResponseEntity<Set<Noticia>> getNoticias(@PathVariable String palavra) {

		Set<Noticia> noticias = new HashSet<Noticia>();
		List<Jornal> jornais = jornalRepository.findAll();

		jornais.forEach(jornal -> populaSetComNoticias(palavra, jornal, noticias));

		return ResponseEntity.ok(noticias);

	}

	@GetMapping("/{nome}/{palavra}")
	public ResponseEntity<Set<Noticia>> getNoticiasPorJornais(@PathVariable String nome, @PathVariable String palavra) {

		Optional<Jornal> jornal = jornalRepository.findByNomeLike("%" + nome + "%");

		if (jornal.isEmpty())
			return ResponseEntity.badRequest().build();

		Set<Noticia> noticias = new HashSet<Noticia>();

		populaSetComNoticias(palavra, jornal.get(), noticias);

		return ResponseEntity.ok(noticias);

	}

	// Extrair para uma classe service
	// Metodo que popula o set com noticias baseado na palavra passada e no jornal
	private void populaSetComNoticias(String palavra, Jornal jornal, Set<Noticia> noticias) {

		try {
			Document document = Jsoup.connect(jornal.getLink()).get();
			Elements elements = document.getElementsByTag("a");

			elements.forEach(e -> {
				String link = e.attr("href");

				if (link.substring(link.lastIndexOf("/") + 1).contains(palavra)) {
					noticias.add(new Noticia(jornal.getNome(), link));
				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
