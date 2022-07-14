package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.edm.model.Comentario;
import ar.edu.unju.edm.service.ComentarioService;
import ar.edu.unju.edm.service.IPeliculaService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class ComentarioController {
    private static final Log GRUPO8 = LogFactory.getLog(BoletoController.class);

	@Autowired
	Comentario nuevocomentario;
	
	@Autowired
	ComentarioService comentarioService;
	
	@Autowired
	IUsuarioService usuarioservice;
	
	@Autowired
	IPeliculaService peliculaservice;

	@GetMapping("/cargarInfo")
		public ModelAndView addInfo() throws NumberFormatException, Exception {
		
		ModelAndView view = new ModelAndView("cargarInfo");
		view.addObject("unaInfo", comentarioService.nuevocomentario());
	//	view.addObject("usuarios", usuarioservice.mostrarUsuarios());
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		System.out.println(userDetail.getUsername());
		view.addObject("usuarioEnSesion",usuarioservice.buscarUsuario(Long.parseLong(userDetail.getUsername())));
		System.out.println(usuarioservice.buscarUsuario(Long.parseLong(userDetail.getUsername())).getApellido());
		view.addObject("peliculas", peliculaservice.listadoPelicula());
		return view;
		}
	
	@PostMapping("/guardarInfo")
	public ModelAndView saveResenia(@Valid @ModelAttribute("unaInfo") Comentario comentarionuevo, BindingResult resultado) {
		ModelAndView view = new ModelAndView();
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		GRUPO8.info("LLENDO NO LLEGANDO");
		if(resultado.hasErrors()) {
			GRUPO8.info("Antes de entrar al error");
			view.setViewName("cargarInfo");
			view.addObject("unainfo", comentarionuevo);
			return view;
		}
		try {
			GRUPO8.info("entro al try");
			comentarionuevo.setUsuario(usuarioservice.buscarUsuario(Long.parseLong(userDetail.getUsername())));
			comentarioService.guardarComentario(comentarionuevo);
			
		}catch(Exception e) {
			GRUPO8.info("catch");
			view.addObject("formReseniaErrorMessage", e.getMessage());
			view.addObject("unaInfo", comentarioService.nuevocomentario());
			GRUPO8.error("Saliendo");
			view.setViewName("cargarInfo");
			return view;
		}
			view.addObject("formReseniaErrorMessage", "Comentario guardado correctamente");
			view.addObject("unaInfo", comentarioService.nuevocomentario());
			view.setViewName("cargarInfo");
			return view;
	}
	
	@GetMapping("/listadoComentario")	
	public ModelAndView showCourses() {
		ModelAndView vista = new ModelAndView("listadoComentario");		
		vista.addObject("listaComentarioValoracion", comentarioService.mostrarcomentario());		
		return vista;
	}
}