package ar.edu.unju.edm.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {
	
	@GetMapping({"/","/index"})
	public String getIndex(Model model) {
		
		return "index";
	}
}
