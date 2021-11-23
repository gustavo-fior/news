package br.com.gx.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/macaco")
public class IndexController {

	@GetMapping
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");

		return mv;
	}

}
