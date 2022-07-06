package ar.edu.unju.edm.controller;

import javax.validation.Valid;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Boleto;
import ar.edu.unju.edm.service.IBoletoService;
import ar.edu.unju.edm.service.IPeliculasService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class BoletoController {
	private static final Log GRUPO8 = LogFactory.getLog(UsuarioController.class);

	@Autowired
	IBoletoService boletoservice;

	@Autowired
	IUsuarioService usuarioservice;

	@Autowired
	IPeliculasService peliculaservice;

	@GetMapping({ "/compra" })
	public ModelAndView addCompra() {
		GRUPO8.info("ingresando al metodo: Nuevo usuario");
		ModelAndView vista = new ModelAndView("cargarCompra");
		vista.addObject("usuariopelicula", boletoservice.nuevoUsuarioCine());
		vista.addObject("usuarios", usuarioservice.listarUsuarios());
		vista.addObject("peliculas", peliculaservice.listarPelicula());
		vista.addObject("editMode", false);
		return vista;
	}

	@PostMapping("/guardarCompra")
	public ModelAndView saveCompra(@Valid @ModelAttribute("boleto") Boleto compraparaguardar,
			BindingResult result) {
		ModelAndView vista = new ModelAndView();
		if (result.hasErrors()) {
			GRUPO8.fatal("Error de validacion");
			vista.addObject("usuariopelicula", compraparaguardar);
			vista.addObject("editMode", false);
			vista.setViewName("cargarCompra");
			return vista;
		}
		try {
			boletoservice.guardarUsuarioCine(compraparaguardar);
		} catch (Exception e) {
			vista.addObject("formUsuarioErrorMessage", e.getMessage());
			vista.addObject("usuariopelicula", compraparaguardar);
			vista.addObject("editMode", false);
			vista.setViewName("cargarCompra");
			return vista;
		}
		vista.addObject("formUsuarioErrorMessage", "Usuario guardado correctamente");
		vista.addObject("unUsuario", boletoservice.nuevoUsuarioCine());
		vista.addObject("editMode", false);
		vista.setViewName("cargarCompra");
		return vista;
	}

	@GetMapping({ "/comentario" })
	public ModelAndView addComentario() {
		// EMILIA.info("ingresando al metodo: Nuevo usuario");
		ModelAndView vista = new ModelAndView("cargarComentario");
		vista.addObject("usuariopelicula", boletoservice.nuevoUsuarioCine());
		vista.addObject("usuarios", usuarioservice.listarUsuarios());
		vista.addObject("peliculas", peliculaservice.listarPelicula());
		vista.addObject("editMode", false);
		return vista;
	}

	@PostMapping("/guardarComentario")
	public ModelAndView saveComentario(@Valid @ModelAttribute("usuariopelicula") Boleto compraparaguardar,
			BindingResult result) {
		ModelAndView vista = new ModelAndView();
		if (result.hasErrors()) {
			GRUPO8.fatal("Error de validacion");
			vista.addObject("usuariopelicula", compraparaguardar);
			vista.addObject("editMode", false);
			vista.setViewName("cargarComentario");
			return vista;
		}
		try {
			boletoservice.guardarUsuarioCine(compraparaguardar);
		} catch (Exception e) {
			vista.addObject("formUsuarioErrorMessage", e.getMessage());
			vista.addObject("usuariopelicula", compraparaguardar);
			vista.addObject("editMode", false);
			vista.setViewName("cargarComentario");
			return vista;
		}
		vista.addObject("formUsuarioErrorMessage", "Usuario guardado correctamente");
		vista.addObject("unUsuario", boletoservice.nuevoUsuarioCine());
		vista.addObject("editMode", false);
		vista.setViewName("cargarComentario");
		return vista;
	}

}
