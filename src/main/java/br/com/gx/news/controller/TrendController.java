package br.com.gx.news.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trends")
public class TrendController {

	@GetMapping
	public ResponseEntity<TrendResponse> getTrends(@Valid TrendRequest req) {
		
	}
	
}
