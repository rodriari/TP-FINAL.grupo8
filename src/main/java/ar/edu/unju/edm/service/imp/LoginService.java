package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ar.edu.unju.edm.model.Usuarios;
import ar.edu.unju.edm.repository.UsuarioRepository;

public class LoginService {
	
	@Autowired 
	UsuarioRepository usuarioRepository;
	
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException { //user detail sobreescribe loadUser.. 
		// TODO Auto-generated method stub
		
		//buscar el usuario
		Usuarios usuarioEncontrado = usuarioRepository.findById(Long.parseLong(dni)).orElseThrow(()->new UsernameNotFoundException("Usuario Invalido"));
		
		//definir autorizacion
		List <GrantedAuthority> tipos = new ArrayList<> (); // hace una lista definiendo el tipo de cada usuario y lo inicializa
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioEncontrado.getTipo());
		tipos.add(grantedAuthority);
		
		//definir el usuario en sesion
		
		UserDetails usuarioEnSesion=new User(dni,usuarioEncontrado.getContraseña(), tipos); //definimo e inicializamo la variable tipo UserDetail
		
		return null;
	}   //implements da funcionalidad a los metodos, extends agarra los metodos y los usa
}

