package ar.edu.unju.edm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CargarpeliController {
	@GetMapping({"/","/Cargarpeli"})
	public String getCargarpeli(Model model) {
		
		return "Cargarpeli";
}
}
