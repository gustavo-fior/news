package br.com.gx.news.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
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

		for (Jornal jornal : jornais) {

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

				return ResponseEntity.badRequest().build();

			}

		}

		return ResponseEntity.ok(noticias);

	}
	
//	@GetMapping("/loop")
//	public ResponseEntity<?> fazLoopDasNoticias() {
//
//		// Loop diario para sempre
//		// Melhorar seleção dos links
//		for (LocalDate data = LocalDate.now(); LocalDate.now().isBefore(LocalDate.MAX); data = data.plusDays(1)) {
//
//			List<Jornal> jornais = jornalRepository.findAll();
//
//			jornais.forEach(jornal -> {
//				
//				try {
//					Document document = Jsoup.connect(jornal.getLink()).get();
//					Elements elements = document.getElementsByTag("a");
//					
//					elements.forEach(e -> {
//						String link = e.attr("href");
//						
//						if(!link.substring(link.lastIndexOf("/") + 1).isEmpty() && link.length() >= 60) {
//							System.out.println(link);
//						}
//						
//					});
//					
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			});
//
//		}
//		return ResponseEntity.ok().build();
//
//	}

}
