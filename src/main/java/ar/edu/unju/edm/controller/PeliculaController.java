package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Peliculas;
import ar.edu.unju.edm.service.IPeliculasService;

@Controller
public class PeliculasController {
	private static final Log GRUPO8 = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	Peliculas nuevaPelicula;
	
	
	@Autowired
	IPeliculasService peliculaService;
	
	//cargar pelicula
	@GetMapping("/otraPelicula")
	public ModelAndView addPelicula() {
		GRUPO8.info("ingresando al metodo: nuevapelicula");
		ModelAndView vista = new ModelAndView("Cargarpeli");
		vista.addObject("pelicula", nuevaPelicula);
		vista.addObject("editMode", false);
		return vista;
	}
	
	@PostMapping(value="/guardarPelicula", consumes = "multipart/form-data")
	public String savePelicula(@Valid @ModelAttribute("pelicula")Peliculas peliculaparaguardar, BindingResult resultado, @RequestParam("file") MultipartFile file, Model model) {
    
		GRUPO8.info("Ingresando al metodo GUARDAR PELICULA");
		
		if(resultado.hasErrors()) {
			GRUPO8.fatal("Error en el meotodo GUARDAR PELICULA");
			
			model.addAttribute("pelicula", peliculaparaguardar);
			model.addAttribute("editMode", false);
			return "Cargarpeli";
		}
		
		try {
			peliculaService.guardarPelicula(peliculaparaguardar);
		}catch(Exception error) {
			model.addAttribute("formPeliculaErrorMessage", error.getMessage());
			model.addAttribute("pelicula", nuevaPelicula);
			model.addAttribute("editMode", false);
			GRUPO8.error("saliendo del metodo: GUARDAR PELICULA");
			return "Cargarpeli";
		}
		
		model.addAttribute("formPeliculaErrorMessage", "Pelicula Guardada Correctamente");
		model.addAttribute("pelicula", nuevaPelicula);
		model.addAttribute("editMode", false);
		GRUPO8.error("saliendo del metodo: GUARDAR PELICULA");
		return "Cargarpeli";
	}
	
	// listar pelicula
		@GetMapping({"/listarPelicula"})	
		public ModelAndView listPelicula() {
			ModelAndView vista = new ModelAndView("ListaPelicula");
			if(peliculaService.listarPelicula().size()!=0) {
				vista.addObject("listapelicula", peliculaService.listarPelicula());
				GRUPO8.info("ingresando al metodo: listapeliculas "+peliculaService.listarPelicula().size());
			}
			return vista;
			}
	
		
		//modificar  pelicula
	@RequestMapping("/editPelicula/{idp}")
	public ModelAndView editMovie(Model model,@PathVariable (name="idp") Long idp)throws Exception {	
		Peliculas peliculaEncontrada = new Peliculas();
		peliculaEncontrada = peliculaService.buscarPelicula(idp);		
		ModelAndView modelView = new ModelAndView("Cargarpeli");
		modelView.addObject("pelicula", peliculaEncontrada);
		 GRUPO8.error("saliendo del metodo: editMovie "+ peliculaEncontrada.getNombrePelicula());
		modelView.addObject("editMode", true);
		return modelView;
	}
	
	
	//actualizar pelicula
	@PostMapping("/editarPelicula")
	public ModelAndView saveEditarpelicula(@Valid @ModelAttribute ("pelicula") Peliculas peliculaparamodificar, BindingResult result) {  
		if(result.hasErrors()) {
			GRUPO8.fatal("Error de validacion");
			ModelAndView vista = new ModelAndView("Cargarpeli");
			vista.addObject("pelicula", peliculaparamodificar);
			vista.addObject("editMode",true);
			return vista;
		}
		try {
			peliculaService.modificarPelicula(peliculaparamodificar);
		}catch(Exception error){
			ModelAndView vista = new ModelAndView("Cargarpeli");
			vista.addObject("formPeliculaErrorMessage", error.getMessage());
			vista.addObject("pelicula", peliculaparamodificar);
			vista.addObject("editMode",true);
			GRUPO8.error("saliendo del metodo: editarPelicula");
			return vista;
		}
			ModelAndView vista = new ModelAndView("listadoPelicula");
			vista.addObject("listaPelicula", peliculaService.listarPelicula());	
			vista.addObject("formPeliculaErrorMessage","Pelicula modificada Correctamente");
		return vista;
	}
		
		/*ModelAndView vista = new ModelAndView("listadoPelicula");
		vista.addObject("listaPelicula", peliculaService.mostrarPeliculas());
		vista.addObject("formPeliculaErrorMessage", "Pelicula Guardado Correctamente");
		return vista;*/
	
	// eliminar pelicula
			@RequestMapping("/deleteMovie/{idp}")
			public String deleteMovie(@PathVariable(name="idp") Long idp, Model model) {
				try {
					peliculaService.eliminarPelicula(idp);
				}catch(Exception error){
					GRUPO8.error("encontrando: eliminarpelicula");
					model.addAttribute("formPeliculaErrorMessage", error.getMessage());
					return "redirect:/otraPelicula";
				}
			
			    return "redirect:/listarPelicula";
			}
	
}
