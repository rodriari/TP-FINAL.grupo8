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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuarios;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UsuarioController {

	private static final Log GRUPO8 = LogFactory.getLog(UsuarioController.class);
	@Autowired
	Usuarios nuevoUsuario;
	
	@Autowired
	IUsuarioService usuarioService;
	
	// cargar usuarios
	@GetMapping({"/otroUsuario"})	
	public ModelAndView addUser() {
		GRUPO8.info("ingresando al metodo: Nuevo usuario");
		ModelAndView vista = new ModelAndView("RegistrarUsuario");
		vista.addObject("usuario", nuevoUsuario);
		vista.addObject("editMode",false);
		return vista;
	}
	
	// guardar usuarios
	@PostMapping("/guardarUsuario")
	public String saveUser(@Valid @ModelAttribute ("usuario") Usuarios usuarioparaguardar, BindingResult result, Model model) {
		if(result.hasErrors()) {
			GRUPO8.fatal("Error de validacion");
			model.addAttribute("usuario", usuarioparaguardar);
			model.addAttribute("editMode", false);
			return "RegistrarUsuario";
		}
			try {
				usuarioService.guardarUsuario(usuarioparaguardar);
			} catch(Exception e) {
				model.addAttribute("formUsuarioErrorMessage", e.getMessage());
				model.addAttribute("usuario", usuarioparaguardar);
				model.addAttribute("editMode", false);
				GRUPO8.error("saliendo del metodo: saveUser");
				return "RegistrarUsuario";
			}
		
			model.addAttribute("formUsuarioErrorMessage", "Usuario guardado correctamente");
			model.addAttribute("usuario", nuevoUsuario);	 
			model.addAttribute("editMode", false);
			return "RegistrarUsuario";

		
	}
	
	// listar usuarios
	@GetMapping({"/listarUsuario"})	
	public ModelAndView listUser() {
		ModelAndView vista2 = new ModelAndView("ListaUsuario");
		if(usuarioService.listarUsuarios().size()!=0) {
		vista2.addObject("listausuarios",usuarioService.listarUsuarios());
		GRUPO8.info("ingresando al metodo: listUsers "+usuarioService.listarUsuarios().get(0).getApellido());
		}
		return vista2;
	}
	// modificar usuario
	@GetMapping("/update/{dni}")
	public ModelAndView modUser(Model model, @PathVariable(name="dni") Long id) throws Exception {
		Usuarios usuarioEncontrado = new Usuarios();
		usuarioEncontrado = usuarioService.buscarUsuario(id);	
		ModelAndView usermod = new ModelAndView("RegistrarUsuario");
	    usermod.addObject("usuario", usuarioEncontrado);
	    GRUPO8.error("saliendo del metodo: modUser "+ usuarioEncontrado.getDni());
	    usermod.addObject("editMode",true);
	    return usermod;
	}
	
	
	@PostMapping("/editarUsuario")
	public ModelAndView savemodUser(@Valid @ModelAttribute ("usuario") Usuarios usuarioparamod, BindingResult result ) {
		/*if(result.hasFieldErrors("nombre") || result.hasFieldErrors("apellido") || result.hasFieldErrors("fechanacimiento") || result.hasFieldErrors("email") ) {*/
			if(result.hasErrors()){
			GRUPO8.fatal("Error de validacion");
			ModelAndView vista = new ModelAndView("RegistrarUsuario");
			vista.addObject("usuario", usuarioparamod);
			vista.addObject("editMode",true);
			return vista;
		}
		try{
			usuarioService.modificarUsuario(usuarioparamod);
		}catch(Exception e){
			ModelAndView vista = new ModelAndView("RegistrarUsuario");
			vista.addObject("formUsuarioErrorMessage", e.getMessage());
			vista.addObject("usuario", usuarioparamod);
			vista.addObject("editMode",true);
			GRUPO8.error("saliendo del metodo: savemodUser");
			return vista;
		}
		 GRUPO8.error("DNI de usuarioparamod "+ usuarioparamod.getDni());
		 GRUPO8.error("Nombre de usuarioparamod "+ usuarioparamod.getNombre());
		ModelAndView vista1 = new ModelAndView("ListaUsuario");		
		vista1.addObject("listausuarios", usuarioService.listarUsuarios());	
		vista1.addObject("formUsuarioErrorMessage","Usuario modificado Correctamente");
		
		return vista1;
	}
	
	
	// eliminar usuarios
	@RequestMapping("/del/{dni}")
	public String deleteUser(@PathVariable(name="dni") Long id, Model model) {
		try {
			usuarioService.eliminarUsuario(id);
		}catch(Exception e){
			GRUPO8.error("encontrando: usuario a eliminar");
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			return "redirect:/otroUsuario";
		}
	    	
	    return "redirect:/listarUsuario";

	}
}
