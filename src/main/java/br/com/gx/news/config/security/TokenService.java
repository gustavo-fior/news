package br.com.gx.news.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.gx.news.modelo.Usuario;
import br.com.gx.news.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${news.jwt.expiration}")
	private String expiration;
	
	@Value("${news.jwt.secret}")
	private String secret;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API de Noticias")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	public Usuario getUserFromToken(String header) {
		if (header == null || header.isEmpty()) {
			return null;
		}
		
		String token = header;

		if (header.startsWith("Bearer ")) {
			token = header.substring(7, header.length());
		}

		Long userId = getIdUsuario(token);
		Usuario user = usuarioRepository.findById(userId).get();

		return user;
	}

}
