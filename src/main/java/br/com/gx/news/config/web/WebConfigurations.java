package br.com.gx.news.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.gx.news.config.security.TokenService;
import br.com.gx.news.interceptor.AcessosInterceptor;
import br.com.gx.news.repository.AcessoRepository;

@Configuration
public class WebConfigurations implements WebMvcConfigurer{

	@Autowired
	private AcessoRepository acessoRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AcessosInterceptor(acessoRepository, tokenService)).addPathPatterns("/**");
	}
	
}
