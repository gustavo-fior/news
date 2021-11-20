package br.com.gx.news.validacao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.gx.news.modelo.Jornal;
import br.com.gx.news.repository.JornalRepository;

@Service
public class ValidacaoJornalService {

	public boolean isJornalInJornais(Jornal jornal, List<Jornal> jornais, JornalRepository repo) {

		for (Jornal j : jornais) {
			Optional<Jornal> jornalOptional = repo.findByLink(jornal.getLink());

			if (jornalOptional.isPresent()) {
				return true;
			}

		}
		return false;

	}

}
