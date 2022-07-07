package ar.edu.unju.edm.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {
	
	@GetMapping({"/","/index","home"})
	public String getIndex(Model model) {
		
		return "index";
	}
	
	@GetMapping({"/login","/Ingreso"})
	public String getLogin(Model model) {
		
		return "login";
	}
}
