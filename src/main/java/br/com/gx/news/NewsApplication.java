package br.com.gx.news;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import br.com.gx.news.modelo.Trend;
import br.com.gx.news.repository.TrendRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
@EnableSwagger2
public class NewsApplication {

	@Autowired
	private TrendRepository trendRepositry;

	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}	

	@PostConstruct
	public void init() {

		Trend trend = new Trend("Clima",
				"https://imagens.climatempo.com.br/climapress/galeria/2021/06/750x553_c8530e867a21dda49534598642ad7c61.jpg",
				"Descrição do clima", "clima");
		Trend trend2 = new Trend("Bolsonaro",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHIcgeJuMGnsqk4K-LqXQcYn5xm9D6eWT_ow&usqp=CAU",
				"Descrição do birojuice", "bolsonaro");
		Trend trend3 = new Trend("Lula",
				"https://www.cartacapital.com.br/wp-content/uploads/2022/01/Sem-T%C3%ADtulo-29-1.jpg",
				"Descrição do molusco", "lula");
		Trend trend4 = new Trend("Neymar Jr",
				"https://www.cnnbrasil.com.br/wp-content/uploads/2021/06/21995_47022457A67E1FF5.jpg",
				"Descrição do menino ney", "neymar");
		
		List<Trend> trends = Arrays.asList(trend, trend2, trend3, trend4);
		
		trendRepositry.saveAll(trends);

	}

}
