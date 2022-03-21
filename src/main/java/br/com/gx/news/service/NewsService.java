package br.com.gx.news.service;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import br.com.gx.news.modelo.Jornal;
import br.com.gx.news.modelo.Noticia;

@Service
public class NewsService {

	public void getSetComNoticiasBaseadoNoJornal(String palavra, Jornal jornal, Set<Noticia> noticias) {

		try {
			Document document = Jsoup.connect(jornal.getLink()).get();
			Elements elements = document.getElementsByTag("a");

			elements.forEach(e -> {
				String link = e.attr("href");

				if (link.substring(link.lastIndexOf("/") + 1).contains(palavra.toLowerCase())) {
					adicionaNoticiasComTituloNoSet(noticias, link, jornal, palavra.toLowerCase());
				}
			});

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private void adicionaNoticiasComTituloNoSet(Set<Noticia> noticias, String link, Jornal jornal, String palavra) {

		try {

			Document documentNoticia = Jsoup.connect(link).get();

			List<String> h1s = documentNoticia.getElementsByTag("h1").eachText();

			String titulo = "";

			for (String h1 : h1s) {

				String h1SemAcentos = Normalizer.normalize(h1, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

				if (h1SemAcentos.toLowerCase().contains(palavra.toLowerCase())) {
					titulo = h1;
					noticias.add(new Noticia(link, jornal.getNome(), titulo));
				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
