package ar.edu.unju.edm.service;

import java.util.List;


import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuarios;

@Service
public interface IUsuarioService {
	public void guardarUsuario(Usuarios usuario);
	public void eliminarUsuario(Long id)throws Exception;
	public void modificarUsuario(Usuarios usuario);
	public List<Usuarios> listarUsuarios(); 
	public Usuarios buscarUsuario(Long id)throws Exception; 
}
