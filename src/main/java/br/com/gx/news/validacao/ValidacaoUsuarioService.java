package br.com.gx.news.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gx.news.modelo.Usuario;
import br.com.gx.news.repository.UsuarioRepository;

@Service
public class ValidacaoUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public boolean isUsuarioUnico(Usuario usuario) {

		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
			return false;
		}

		return true;

	}

}
