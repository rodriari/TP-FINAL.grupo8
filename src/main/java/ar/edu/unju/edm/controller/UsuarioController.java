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
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UsuarioController {

	private static final Log GRUPO8 = LogFactory.getLog(UsuarioController.class);

	@Autowired
	Usuario nuevoUsuario;

	@Autowired
	IUsuarioService serviceUsuario;

	@GetMapping("/otroUsuario") // entra
	public ModelAndView addUser() {
		GRUPO8.info("ingresando al metodo: Crear usuario");
		ModelAndView vista = new ModelAndView("cargarUsuario");// pasa nombre de la lista a pasar
		vista.addObject("usuario", nuevoUsuario);
		vista.addObject("editMode", false);
		return vista;
	}

	// guardar usuarios
	@PostMapping("/guardarUsuario")
	public String saveUser(@Valid @ModelAttribute("usuario") Usuario usuarioparaguardar, BindingResult resultado,
			Model model) { // del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y
							// un nombre
		System.out.println(resultado.getAllErrors());

		if (resultado.hasErrors()) {
			GRUPO8.fatal("Error de Validacion");
			model.addAttribute("usuario", usuarioparaguardar);
			model.addAttribute("editMode", false);
			return "cargarUsuario";
		}
		try { // controla si algo se ejecuta bien
			serviceUsuario.guardarUsuario(usuarioparaguardar);
		} catch (Exception error) { // si no sale por aqui
			model.addAttribute("formUsuarioErrorMessage", error.getMessage());
			model.addAttribute("usuario", usuarioparaguardar);
			model.addAttribute("editMode", false);
			GRUPO8.error("saliendo del metodo: saveUser");
			return "cargarUsuario";
		}
		model.addAttribute("formUsuarioErrorMessage", "Usuario Guardado Correctamente");
		model.addAttribute("usuario", nuevoUsuario);
		model.addAttribute("editMode", false);
		return "cargarUsuario";
	}

	// listar usuarios
	@GetMapping("/listadoUsuario")
	public ModelAndView listUser() {
		ModelAndView vista2 = new ModelAndView("listadoUsuario");
		vista2.addObject("listaUsuario", serviceUsuario.mostrarUsuarios());
		vista2.addObject("listaUsuarioInactivo", serviceUsuario.mostrarUsuariosInactivos());
		return vista2;
	}

	// eliminar
	@RequestMapping("/eliminarUsuario/{dni}")
	public String deleteUser(@PathVariable(name = "dni") Long dni, Model model) {
		try {
			serviceUsuario.eliminarUsuario(dni);
		} catch (Exception error) {
			GRUPO8.error("Error en eliminar usuario");
			model.addAttribute("formUsuarioErrorMessage", error.getMessage());
			return "redirect:/listadoUsuario";
		}
		return "redirect:/listadoUsuario";
	}

	// modificar usuario
	@GetMapping("/editarUsuario/{dni}")
	public ModelAndView edituser(Model model, @PathVariable(name = "dni") Long dni) throws Exception {
		Usuario usuarioEncontrado = new Usuario();
		usuarioEncontrado = serviceUsuario.buscarUsuario(dni);
		ModelAndView modelView = new ModelAndView("cargarUsuario");
		modelView.addObject("usuario", usuarioEncontrado);
		GRUPO8.info("saliendo del metodo: editar usuario get mapping" + usuarioEncontrado.getDni());
		modelView.addObject("editMode", true);
		return modelView;
	}

	@PostMapping("/editarUsuario")
	public ModelAndView postEditarUsuario(@ModelAttribute("usuario") Usuario usuarioparamodificar,
			BindingResult result) {
		
		if (result.hasErrors()) {
			GRUPO8.fatal("Error de validacion");
			ModelAndView vista = new ModelAndView("cargarUsuario");
			vista.addObject("usuario", usuarioparamodificar);
			vista.addObject("editMode", true);
			return vista;
		}
		try {
			serviceUsuario.modificarUsuario(usuarioparamodificar);
		} catch (Exception error) {
			ModelAndView vista = new ModelAndView("cargarUsuario");
			vista.addObject("formUsuarioErrorMessage", error.getMessage());
			vista.addObject("usuario", usuarioparamodificar);
			vista.addObject("editMode", true);
			GRUPO8.error("saliendo del metodo: editar usuario");
			return vista;
		}
		GRUPO8.info("DNI de usuarioparamod " + usuarioparamodificar.getDni());
		GRUPO8.info("Nombre de usuarioparamod " + usuarioparamodificar.getNombre());
		ModelAndView vista1 = new ModelAndView("listadoUsuario");
		vista1.addObject("listaUsuario", serviceUsuario.mostrarUsuarios());
		vista1.addObject("listaUsuarioInactivo", serviceUsuario.mostrarUsuariosInactivos());
		vista1.addObject("formUsuarioErrorMessage", "Usuario modificado Correctamente");

		return vista1;
	}
}
