package ar.edu.unju.edm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControlller {
	@GetMapping({"/","/Login"})
	public String getLogin(Model model) {
		
		return "login";
}
}