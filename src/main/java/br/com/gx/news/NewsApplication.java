package br.com.gx.news;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import br.com.gx.news.modelo.Jornal;
import br.com.gx.news.modelo.Perfil;
import br.com.gx.news.modelo.Usuario;
import br.com.gx.news.repository.JornalRepository;
import br.com.gx.news.repository.PerfilRepository;
import br.com.gx.news.repository.UsuarioRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
@EnableSwagger2
public class NewsApplication {

	@Autowired
	private JornalRepository jornalRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void populaBanco() {

		salvaJornais();
		criaPerfis();
		criaUserAdmin();

	}

	private void salvaJornais() {

		List<Jornal> jornais = new ArrayList<>();

		jornais.add(new Jornal("https://noticias.uol.com.br", "Uol"));
		jornais.add(new Jornal("https://g1.globo.com/jornal-nacional/", "Jornal Nacional"));
		jornais.add(new Jornal("https://www.correiobraziliense.com.br", "Correio Braiziliense"));
		jornais.add(new Jornal("https://agenciabrasil.ebc.com.br", "Agencia Brasil EBC"));
		jornais.add(new Jornal("https://oglobo.globo.com", "O Globo"));
		jornais.add(new Jornal("https://g1.globo.com", "G1"));
		jornais.add(new Jornal("https://www.estadao.com.br", "Estadao"));
		jornais.add(new Jornal("https://www.folha.uol.com.br", "Folha de Sao Paulo"));
		jornais.add(new Jornal("https://www.r7.com", "R7"));

		jornalRepository.saveAll(jornais);

	}

	private void criaPerfis() {

		Perfil perfil = new Perfil();
		perfil.setNome("ROLE_USER");

		Perfil perfil2 = new Perfil();
		perfil2.setNome("ROLE_ADMIN");

		perfilRepository.save(perfil);
		perfilRepository.save(perfil2);

	}

	private void criaUserAdmin() {

		Usuario usuario = new Usuario();
		usuario.setEmail("gustavo_fior@outlook.com");
		usuario.setSenha("$2a$10$6IsNcH0MQ6q0olW44k1lKOWkFFRLQzMx74xrv0H8JVqX7nA7MV5XW");
		usuario.setNome("Gustavo Fior");
		usuario.adicionarPerfil(perfilRepository.findByNome("ROLE_ADMIN"));
		

		usuarioRepository.save(usuario);

	}

}
