package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.controller.UsuarioController;
import ar.edu.unju.edm.model.Usuarios;
import ar.edu.unju.edm.repository.UsuarioRepository;
import ar.edu.unju.edm.service.IUsuarioService;
import ar.edu.unju.edm.util.ListaUsuario;

@Service
public class IUsuarioServiceImp implements IUsuarioService{

	private static final Log GRUPO8 = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	ListaUsuario lista;
	
	@Autowired 
	UsuarioRepository usuarioRepository;
	
	@Override
	public void guardarUsuario(Usuarios usuarioparaguardar) {
		// TODO Auto-generated method stub
		usuarioparaguardar.setEstado(true);
        String pw = usuarioparaguardar.getContraseña();// se asigna la contraseña a pw
        BCryptPasswordEncoder coder = new BCryptPasswordEncoder(4);//encripta lo que nosotros queramos, 4 nivel de seguridad
        usuarioparaguardar.setContraseña(coder.encode(pw));
        usuarioRepository.save(usuarioparaguardar);
    }

	@Override
	public void eliminarUsuario (Long dni) throws Exception {
		// TODO Auto-generated method stub		
		Usuarios auxiliar =new Usuarios();
        auxiliar=buscarUsuario(dni);
        usuarioRepository.delete(auxiliar);
	}

	@Override
	public void modificarUsuario(Usuarios usuario) {
		// TODO Auto-generated method stub
		usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuarios> listarUsuarios() {
		// TODO Auto-generated method stub
		List<Usuarios> auxiliar = new ArrayList<>();
        GRUPO8.info("ingresando al metodo arraylist: listar usuarios");
        auxiliar=(List<Usuarios>) usuarioRepository.findAll();
        return auxiliar;
    }

	

	@Override
	public Usuarios buscarUsuario(Long dni) throws Exception {
		// TODO Auto-generated method stub
		Usuarios usuarioEncontrado = new Usuarios();
		usuarioEncontrado=usuarioRepository.findById(dni).orElseThrow(()->new Exception("usuario no encontrado"));
		return usuarioEncontrado;
	}

}