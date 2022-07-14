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
import ar.edu.unju.edm.service.IPeliculaService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class BoletoController {
private static final Log GRUPO8 = LogFactory.getLog(BoletoController.class);

	@Autowired 
	IBoletoService boletoservice;
	
	@Autowired
	IUsuarioService usuarioservice;
	
	@Autowired
	IPeliculaService peliculaservice;
	
	@GetMapping({"/compra"})	
	public ModelAndView addCompra() {
		GRUPO8.info("ingresando al metodo comprar entrada");
		ModelAndView vista = new ModelAndView("cargarCompra");
		vista.addObject("boleto", boletoservice.nuevoUsuarioCine() );
		vista.addObject("usuarios", usuarioservice.mostrarUsuarios() );
		vista.addObject("peliculas", peliculaservice.listadoPelicula() );
		vista.addObject("editMode",false);
		GRUPO8.info("SALIENDO DEL COMPRAR ENTRADA");
		return vista;
	}
	
	
	@PostMapping("/guardarCompra")
	public ModelAndView saveCompra(@Valid @ModelAttribute ("boleto") Boleto compraparaguardar, BindingResult result) {
		GRUPO8.fatal("guardando compra...");
		ModelAndView vista=new ModelAndView ();
		if(result.hasErrors()) {
			GRUPO8.fatal("Error en el metodo guardar compra...");
			vista.addObject("boleto", compraparaguardar);
			vista.addObject("editMode", false);
			vista.setViewName("cargarCompra");
			return vista;
		}
			try {
				boletoservice.guardarUsuarioCine(compraparaguardar);
			} catch(Exception e) {
				vista.addObject("formCompraErrorMessage", e.getMessage());
				vista.addObject("boleto", compraparaguardar);
				GRUPO8.error("saliendo del metodo: guardar compra...");
				vista.addObject("editMode", false);
				vista.setViewName("cargarCompra");
				return vista;
			}
		
			vista.addObject("formCompraErrorMessage", "compra guardada correctamente");
			vista.addObject("boleto", boletoservice.nuevoUsuarioCine());
			vista.addObject("editMode", false);
			vista.setViewName("cargarCompra");
			return vista;

	}
	@GetMapping("/listadocompra")
	public ModelAndView listUser() {
		ModelAndView vista2 = new ModelAndView("listadoCompra");
		vista2.addObject("listaCompra", boletoservice.listadoUsuariosCine());
		return vista2;
	}
	
	@GetMapping({"/comentario"})	
	public ModelAndView addComentario() {
		GRUPO8.info("ingresando al metodo: Nuevo comentario");
		ModelAndView vista1 = new ModelAndView("cargarComentario");
		vista1.addObject("boleto", boletoservice.nuevoUsuarioCine() );
		vista1.addObject("usuarios", usuarioservice.mostrarUsuarios() );
		vista1.addObject("peliculas", peliculaservice.listadoPelicula() );
		vista1.addObject("editMode",false);
		return vista1;
	}
	
	@PostMapping("/guardarComentario")
	public ModelAndView saveComentario(@Valid @ModelAttribute ("boleto") Boleto comentarioparaguardar, BindingResult result) {
		ModelAndView vista1=new ModelAndView ();
		if(result.hasErrors()) {
			GRUPO8.fatal("Error de validacion");
			vista1.addObject("boleto", comentarioparaguardar);
			vista1.addObject("editMode", false);
			vista1.setViewName("cargarComentario");
			return vista1;
		}
			try {
				boletoservice.guardarUsuarioCine(comentarioparaguardar);
			} catch(Exception e) {
				vista1.addObject("formUsuarioErrorMessage", e.getMessage());
				vista1.addObject("boleto", comentarioparaguardar);
				GRUPO8.error("saliendo del metodo: ERRORRRRRRRRRRRRRR");
				vista1.addObject("editMode", false);
				vista1.setViewName("cargarComentario");
				return vista1;
			}
			return vista1;
	}
	
	
	@GetMapping({"/valoracion"})	
	public ModelAndView addValoracion() {
		GRUPO8.info("ingresando al metodo: valoracion");
		ModelAndView vista = new ModelAndView("cargarValoracion");
		vista.addObject("boleto", boletoservice.nuevoUsuarioCine() );
		vista.addObject("usuarios", usuarioservice.mostrarUsuarios() );
		vista.addObject("peliculas", peliculaservice.listadoPelicula() );
		vista.addObject("editMode",false);
		return vista;
	}
	
	@PostMapping("/guardarValoracion")
	public ModelAndView saveValoracion(@Valid @ModelAttribute ("boleto") Boleto compraparaguardar, BindingResult result) {
		ModelAndView vista=new ModelAndView ();
		if(result.hasErrors()) {
			GRUPO8.fatal("Error de validacion");
			vista.addObject("boleto", compraparaguardar);
			vista.addObject("editMode", false);
			vista.setViewName("cargarValoracion");
			return vista;
		}
			try {
				boletoservice.guardarUsuarioCine(compraparaguardar);
			} catch(Exception e) {
				vista.addObject("formUsuarioErrorMessage", e.getMessage());
				vista.addObject("boleto", compraparaguardar);
				GRUPO8.error("saliendo del metodo: ERROOOOOOOOOOOOOOOOR");
				vista.addObject("editMode", false);
				vista.setViewName("cargarValoracion");
				return vista;
			}
		
			vista.addObject("formUsuarioErrorMessage", "Valoracion guardado correctamente");
			vista.addObject("unUsuario", boletoservice.nuevoUsuarioCine());
			vista.addObject("editMode", false);
			vista.setViewName("cargarValoracion");
			return vista;
	}
	
}
