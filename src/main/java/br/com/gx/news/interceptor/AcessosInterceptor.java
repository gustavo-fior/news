package br.com.gx.news.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;

import br.com.gx.news.modelo.Acesso;
import br.com.gx.news.repository.AcessoRepository;

public class AcessosInterceptor implements AsyncHandlerInterceptor {

	private AcessoRepository acessoRepository;

	public AcessosInterceptor(AcessoRepository acessoRepository) {
		this.acessoRepository = acessoRepository;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Acesso acesso = new Acesso();
		acesso.setPath(request.getRequestURI());
		acesso.setData(LocalDateTime.now());

		request.setAttribute("acesso", acesso);

		return true;

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		Acesso acesso = (Acesso) request.getAttribute("acesso");

		acesso.setDuracao(Duration.between(acesso.getData(), LocalDateTime.now()));

		acesso.setHttpCode(response.getStatus());

		acessoRepository.save(acesso);

	}

}
